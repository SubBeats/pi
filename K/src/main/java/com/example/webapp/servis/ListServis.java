package com.example.webapp.servis;

import com.example.webapp.Mod.List;
import com.example.webapp.Mod.Order;
import com.example.webapp.Mod.models;
import com.example.webapp.repozitory.OrderRepository;
import com.example.webapp.repozitory.ProdListRepository;
import com.example.webapp.repozitory.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;

@Service
@Slf4j//логирование
@RequiredArgsConstructor//
public class ListServis {
    private final ProjectRepository projectRepository;
    private final ProdListRepository prodListRepository;
    private final OrderRepository orderRepository;
    /*public void save(String title, Order order, List list, Time in, Time out, Date d){
        models mdl= null;
        for(models M : projectRepository.findByTitle(title)){
            mdl = M;
        }
        if(!order.FindList(title)) {
            log.info("Saving Title{}", title);
            mdl.addListToProduct(list);
            order.addListToOrder(list);
            list.setOrderDate(d);
            list.setTimeIn(in);
            list.setTimeOut(out);
            prodListRepository.save(list);
        }
    }*/

    public int Sum(Order order){
        //SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        int lil = 0 ;
        for(List L : order.getLists()){
           lil+=Comparable(L.getOrderDate1(),L.getOrderDate2(),prodListRepository.findByOrder(order));
           log.info("Итог сумма {}",lil);
        }
        return lil;
    }
    public void save2(Long id, Order order, List list, LocalDateTime in, LocalDateTime out){
            models mdl= projectRepository.findByID(id);
        /*if(!order.FindList(title)) {*/
        java.util.List<List> arr = prodListRepository.findByModel(mdl);
        if(arr == null) {
            if ((in != null) && (out != null) && (in.isBefore(out))) {
                log.info("Saving id {}", id);
                mdl.addListToProduct(list);
                log.info("order {} ", list.getOrder());
                order.addListToOrder(list);
                list.setOrderDate1(in);
                list.setOrderDate2(out);
                prodListRepository.save(list);
            }
        }
        else {
            for(List A : arr) {
                if ((A.getOrderDate2().isBefore(in) && A.getOrderDate1().isAfter(out))) {
                    log.info("Saving id {}", id);
                    mdl.addListToProduct(list);
                    log.info("order {} ", list.getOrder());
                    order.addListToOrder(list);
                    list.setOrderDate1(in);
                    list.setOrderDate2(out);
                    prodListRepository.save(list);
                }
            }
        }

    }

    /*
    public void save2(Long id, Order order, List list, LocalDateTime in, LocalDateTime out) {
        models mdl = projectRepository.findByID(id);
        */
    /*if(!order.FindList(title)) {*//*

        if ((in != null) && (out != null) && (in.isBefore(out))) {
            List arr = prodListRepository.findByModel(mdl);
            if(arr != null) {
                if ((arr.getOrderDate1().isBefore(in) && arr.getOrderDate2().isBefore(out)) || (arr.getOrderDate1().isAfter(in) && arr.getOrderDate2().isAfter(out))) {
                    Save3(order,list,in,out,mdl);
                }
            }
            else Save3(order,list,in,out,mdl);

        }
    }

    public void Save3 (Order order, List list, LocalDateTime in, LocalDateTime out,models mdl){
        mdl.addListToProduct(list);
        log.info("order {} ", list.getOrder());
        order.addListToOrder(list);
        list.setOrderDate1(in);
        list.setOrderDate2(out);

        prodListRepository.save(list);
    }
*/


    public void delete(Long id,Order order){
        for(List L : prodListRepository.findAll()){
            //log.info("delete listId{}",L.getID());
            if(L.getModel().getID().equals(id)){
                //order.deleteListFromOrder(L);
                //L.getModel().deleteListFromModel(L);
                prodListRepository.delete(L);
            }
        }
    }
    public java.util.List<models> listprod(Order order){
        java.util.List<models> mdl = new ArrayList<>();
        for(List L : prodListRepository.findByOrder(order)){
            mdl.add(L.getModel());
        }
        return mdl;
    }

    public int Comparable(LocalDateTime i1, LocalDateTime i2, java.util.List<List> lists){
        int FinalSum=0;
       int sum1 = i2.getDayOfYear() - i1.getDayOfYear();
       log.info("min year {}",sum1);
       int sum3 = i2.getHour() - i1.getHour();
       log.info("Hour{}",sum3);
       for(List L : lists){
           FinalSum += L.getModel().getPrice()*sum1*24;
           FinalSum += L.getModel().getPrice()*sum3;
       }
        log.info("Final {} ",FinalSum);
       return (FinalSum);
    }
 }
