package com.example.invoicecreatorservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Test_situation {

    @Test
    void Test_case(){
        String greeting = "Hello";

        assertEquals("Hello", greeting);

    }
}
