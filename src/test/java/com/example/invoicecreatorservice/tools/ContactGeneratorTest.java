package com.example.invoicecreatorservice.tools;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ContactGeneratorTest {

    @Test
    void codeGenerationTest() {
        ContactGenerator generator = new ContactGenerator();

        String newCode = generator.generateCode();

        assertTrue(newCode.length() == 10);
    }
}
