package com.example.webapp.repozitory;

import com.example.webapp.Mod.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}