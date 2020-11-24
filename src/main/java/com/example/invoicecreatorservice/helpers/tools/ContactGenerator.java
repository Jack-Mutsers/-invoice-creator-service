package com.example.invoicecreatorservice.helpers.tools;

import java.util.Random;

public class ContactGenerator {
    Random r = new Random();

    public String generateCode(){

        StringBuilder code = new StringBuilder();
        String alphabet = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < 10; i++) {
            code.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }

        return code.toString();
    }
}
