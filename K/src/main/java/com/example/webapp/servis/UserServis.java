package com.example.webapp.servis;

import com.example.webapp.Mod.User;
import com.example.webapp.Mod.enums.Role;
import com.example.webapp.repozitory.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServis {
    private final UserRepository Repository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user){
        String userEmail = user.getEmail();
        if (Repository.findByEmail(userEmail) != null) return false;
        user.setActive(true);
        user.getRoles().add(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Saving new User with email: {}", userEmail);
        Repository.save(user);
        return true;
    }

    public void delete(Long id){
        Repository.deleteById(id);
    }


    public User getProd(String email){
        return Repository.findByEmail(email);
    }
}
