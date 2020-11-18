package com.example.invoicecreatorservice.tools;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncoder {

    private static BCryptEncoder myself;
    private BCryptPasswordEncoder passwordEncoder;

    private BCryptEncoder(){
        passwordEncoder = new BCryptPasswordEncoder();
    }

    public static BCryptEncoder getInstance(){
        if(myself == null){
            myself = new BCryptEncoder();
        }

        return myself;
    }

    public String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

    public boolean validatePassword(String password, String encodedPassword){
        return passwordEncoder.matches(password, encodedPassword);
    }
}
