package com.example.webapp.servis;

import com.example.webapp.Mod.Order;
import com.example.webapp.Mod.User;
import com.example.webapp.Mod.models;
import com.example.webapp.repozitory.OrderRepository;
import com.example.webapp.repozitory.ProdListRepository;
import com.example.webapp.repozitory.ProjectRepository;
import com.example.webapp.repozitory.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j//логирование
@RequiredArgsConstructor//

public class OrderServis {
    private final OrderRepository orderRepository;
    private final ProdListRepository prodListRepository;
    private final UserRepository userRepository;

    public List<Order> listOrder(Principal principal){
        int i = orderRepository.findByUser((getUserByPrincipal(principal))).size();
        log.info("list_of_orders{}",i);
        if(i!=0) {
            return orderRepository.findByUser((getUserByPrincipal(principal)));
        }
        return new ArrayList<>();
    }

    public User getUserByPrincipal(Principal principal){
        if(principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public void saveOrder(Order order, Principal principal) {
        order.setUser(getUserByPrincipal(principal));
        int s = orderRepository.findByUser(getUserByPrincipal(principal)).size();
        if(s !=0){
            order.setNum(s+1);
        }
        else order.setNum(1);
        log.info("Saving new Order. id{}",order.getID());
        orderRepository.save(order);
    }

    public List<models> getListModels(Order order) {
        List<com.example.webapp.Mod.List> array = prodListRepository.findByOrder(order);
        if(array.size() != 0) {
            log.info("find smf");
                        //List<com.example.webapp.Mod.List> iter = order.getLists();
            List <models> models = new ArrayList<>();
            for(com.example.webapp.Mod.List l : array) {
                models.add(l.getModel());
                if (l.getModel() != null) {
                    log.info("title : {}", l.getModel().getTitle());
                }
            }
            return models;
        }
        log.info("Empty");
        return new ArrayList<>();
    }

/*    public List<models> getListModels(Order order,String title) {
        List<com.example.webapp.Mod.List> array = prodListRepository.findByOrder(order);
        if(array.size() != 0) {
            log.info("find smf");
            List <models> models = new ArrayList<>();
            for(com.example.webapp.Mod.List l : array) {
                if(title != null )  {
                    if (l.getModel().getTitle().equals(title)) {
                        models.add(l.getModel());
                    }
                }
                else models.add(l.getModel());
                if (l.getModel() != null) {
                    log.info("title : {}", l.getModel().getTitle());
                }
            }
            return models;
        }
        log.info("Empty");
        return new ArrayList<>();
    }*/
}

