package com.example.invoicecreatorservice.objects.models;

import com.example.invoicecreatorservice.objects.data_transfer_objects.CustomerForAlterationDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class JwtRequestTest {

    @Test
    void instantiateEntity(){
        String username = "henk";
        String password = "Password1!";

        JwtRequest entity = new JwtRequest(
            username,
            password
        );

        assertEquals(username, entity.getUsername());
        assertEquals(password, entity.getPassword());
    }

    @Test
    void instantiateEmptyEntity(){

        JwtRequest entity = new JwtRequest();

        assertNull(entity.getUsername());
        assertNull(entity.getPassword());
    }

    @Test
    void fillEmptyEntity(){
        String username = "henk";
        String password = "Password1!";

        JwtRequest entity = new JwtRequest();

        entity.setUsername(username);
        entity.setPassword(password);

        assertEquals(username, entity.getUsername());
        assertEquals(password, entity.getPassword());
    }

}
