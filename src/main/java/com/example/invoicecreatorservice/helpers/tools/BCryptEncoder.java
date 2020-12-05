package com.example.invoicecreatorservice.helpers.tools;

import com.example.invoicecreatorservice.contracts.tools.IPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncoder implements IPasswordEncoder {

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
