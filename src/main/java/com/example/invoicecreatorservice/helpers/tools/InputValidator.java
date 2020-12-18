package com.example.invoicecreatorservice.helpers.tools;

public class InputValidator {

    private InputValidator(){

    }

    public static boolean validateStringValue(String value){
        return value == null || value.isBlank();
    }
}
