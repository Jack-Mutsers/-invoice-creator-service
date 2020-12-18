package com.example.invoicecreatorservice.helpers.tools;

import java.util.List;

public class Helper {

    private Helper(){

    }

    public static boolean validateStringValue(String value){
        return value == null || value.isBlank();
    }

    public static <T> Iterable<T> emptyIfNull(Iterable<T> iterable) {
        return iterable == null ? List.of() : iterable;
    }
}
