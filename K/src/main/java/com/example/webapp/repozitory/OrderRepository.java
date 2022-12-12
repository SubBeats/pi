package com.example.webapp.repozitory;

import com.example.webapp.Mod.Order;
import com.example.webapp.Mod.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    //Order findByUser_id(Long id);
    List<Order> findByUser(User user);
    Order findByID(Long id);
}
