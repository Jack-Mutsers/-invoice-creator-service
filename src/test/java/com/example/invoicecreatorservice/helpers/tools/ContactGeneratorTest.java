package com.example.invoicecreatorservice.helpers.tools;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class ContactGeneratorTest {

    @Test
    void codeGenerationTest() {
        ContactGenerator generator = new ContactGenerator();

        String firstCode = generator.generateCode();
        String secondCode = generator.generateCode();

        assertEquals(10, firstCode.length());
        assertEquals(10, secondCode.length());
        assertNotEquals(firstCode, secondCode);
    }
}
