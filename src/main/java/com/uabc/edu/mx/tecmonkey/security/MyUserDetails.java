package com.uabc.edu.mx.tecmonkey.security;

import com.uabc.edu.mx.tecmonkey.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    private String email;
    private String fullname;
    private String userName;
    private String password;
    private boolean active;
    private List<GrantedAuthority> roles;


    public MyUserDetails(User user) {
        this.email = user.getEmail();
        this.fullname = user.getFullname();
        this.userName = user.getUsername();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.roles = Arrays.stream(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();


        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullname;
    }
}
