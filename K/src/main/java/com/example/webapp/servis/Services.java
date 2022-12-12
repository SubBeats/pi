package com.example.webapp.servis;

import com.example.webapp.Mod.Image;
import com.example.webapp.Mod.Order;
import com.example.webapp.Mod.User;
import com.example.webapp.repozitory.OrderRepository;
import com.example.webapp.repozitory.ProjectRepository;
import com.example.webapp.Mod.models;
import com.example.webapp.repozitory.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j//логирование
@RequiredArgsConstructor//
public class Services {
    private final OrderRepository orderRepository;
    private final ProjectRepository productRepository;
    private final UserRepository userRepository;

    public List<models> listprod(String title){
        if(title != null) { log.info("title{}",title); return productRepository.findByTitle(title);}
        else return productRepository.findAll();
    }


        /*public void Save(Principal principal, Order order) throws IOException {
        order.setUser(getUserByPrincipal(principal));
        Image image1;
        Image image2;
        Image image3;
        if(item1.getSize() != 0){
            image1 = toImageEntity(item1);
            image1.setIsPreviewImage(true);
            mdl.addImageToProduct(image1);
        }
        if(item2.getSize() != 0){
            image2 = toImageEntity(item2);
            mdl.addImageToProduct(image2);
        }
        if(item3.getSize() != 0){
            image3 = toImageEntity(item3);
            mdl.addImageToProduct(image3);
        }
        log.info("Saving new Product. Id :{}", order.getID());
        Order orderFromBD = orderRepository.save(order);
        models modelFromDB = productRepository.save(mdl);
        modelFromDB.setPreviewImageId(modelFromDB.getImages().get(0).getID());
        productRepository.save(mdl);
    }*/

    public User getUserByPrincipal(Principal principal) {
        if(principal == null) {return new User();}
        return userRepository.findByEmail(principal.getName());
    }
    /*public Order getOrderByUserId(User user){
        if(user == null) return new Order();
       return orderRepository.findByUser_id(user.getID());
    }*/


    private Image toImageEntity(MultipartFile file) throws IOException {//перевод из мультипарф файла в фото
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void delete(Long id){
        orderRepository.deleteById(id);
    }


    public models getProd(Long id){
        return productRepository.findById(id).orElse(null);
    }
}
