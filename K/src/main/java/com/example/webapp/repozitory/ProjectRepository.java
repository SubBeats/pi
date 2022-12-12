package com.example.webapp.repozitory;

import com.example.webapp.Mod.models;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectRepository extends JpaRepository<models,Long> {
    List <models> findByTitle (String title);
    models  findByID(Long id);
}
