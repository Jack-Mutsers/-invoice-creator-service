package com.example.invoicecreatorservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class Test_situation {

    @Test
    public void Test_case(){
        String greeting = "Hallo";

        assertEquals("Hallo", greeting);
    }
}
