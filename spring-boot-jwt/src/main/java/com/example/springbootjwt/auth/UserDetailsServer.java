package com.example.springbootjwt.auth;

import jakarta.annotation.PostConstruct;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class UserDetailsServer implements org.springframework.security.core.userdetails.UserDetailsService{
    private Map<String,String> users=new HashMap<>();
    @PostConstruct
    public void init(){
        users.put("temelt","1234");

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
