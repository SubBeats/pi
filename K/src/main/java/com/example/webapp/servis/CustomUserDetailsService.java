package com.example.webapp.servis;

import com.example.webapp.repozitory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository UsRep;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return UsRep.findByEmail(email);
    }
}
