package com.example.invoicecreatorservice.helpers.tools;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BCryptEncoderTest {
    BCryptEncoder encoder = BCryptEncoder.getInstance();

    @Test
    void encodePasswordTest(){
        String password = "Password1!";

        String encodedPassword = encoder.encodePassword(password);

        assertNotEquals(password, encodedPassword);
    }

    @Test
    void validatePasswordTest(){
        String password = "Password1!";
        String encodedPassword = "$2a$10$vXNmL91UI1nOIuzMrUe.HONLO.Imwl/xaiIn.HDY8P.JWKnejOA6u";

        boolean valid = encoder.validatePassword(password, encodedPassword);

        assertTrue(valid);
    }
}
