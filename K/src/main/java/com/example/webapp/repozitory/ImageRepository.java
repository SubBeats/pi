package com.example.webapp.repozitory;

import com.example.webapp.Mod.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
