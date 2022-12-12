package com.example.webapp.Mod;

import com.example.webapp.Mod.enums.Role;
import com.example.webapp.Mod.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

@Entity
@Table(name = "User")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long ID;
    private String number;
    @Column ( name = "email",unique = true)
    private String email;
    private String name;
    private boolean active;
    //@OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private String password;
    @ElementCollection (targetClass = Role.class, fetch = FetchType.EAGER)//загрузка ролей
    @CollectionTable(name = "join_role",
    joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "user")
    private List<Order> orders = new ArrayList<>();
    private LocalDateTime dateOfCreate;
    @PrePersist
    private void init(){
        dateOfCreate = LocalDateTime.now();
    }

    //security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
