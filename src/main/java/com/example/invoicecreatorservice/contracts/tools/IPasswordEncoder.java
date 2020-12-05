package com.example.invoicecreatorservice.contracts.tools;

public interface IPasswordEncoder {
    String encodePassword(String password);
    boolean validatePassword(String password, String encodedPassword);
}
