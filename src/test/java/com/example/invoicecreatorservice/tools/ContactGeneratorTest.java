package com.example.invoicecreatorservice.tools;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ContactGeneratorTest {

    @Test
    void codeGenerationTest() {
        ContactGenerator generator = new ContactGenerator();

        String newCode = generator.generateCode();

        assertEquals(10, newCode.length());
    }
}
