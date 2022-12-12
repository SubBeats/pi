package com.example.webapp.repozitory;

import com.example.webapp.Mod.List;
import com.example.webapp.Mod.Order;
import com.example.webapp.Mod.models;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdListRepository extends JpaRepository<List,Long> {
    //java.util.List<List> findByID(long id);
    java.util.List<List> findByOrder(Order order);
    java.util.List<List> findByModel (models model);

}
