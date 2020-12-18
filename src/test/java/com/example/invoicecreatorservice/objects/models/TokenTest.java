package com.example.invoicecreatorservice.objects.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TokenTest {

    @Test
    void instantiateEntityWithId(){
        int id = 5;
        String jwtToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYWNrIiwiY29tcGFueUlkIjoxLCJleHAiOjE2MDc3NjY2MDMsImlhdCI6MTYwNzc2MzAwMywidXNlcklkIjoxfQ.-1TCXwxz1X0miPr0pOsiEvilZE0zHWOXnyjXajbJtbalGDG0hiS6awvnb1PzCC3kXbYe807JFYEHtMXicnhi1Q";
        String ip = "0:0:0:0:0:0:0:1";

        Token entity = new Token(
            id,
            jwtToken,
            ip,
            true
        );

        assertEquals(id, entity.getId());
        assertEquals(jwtToken, entity.getToken());
        assertEquals(ip, entity.getIp());
        assertTrue(entity.isValid());
    }

    @Test
    void instantiateEntityWithoutId(){
        String jwtToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYWNrIiwiY29tcGFueUlkIjoxLCJleHAiOjE2MDc3NjY2MDMsImlhdCI6MTYwNzc2MzAwMywidXNlcklkIjoxfQ.-1TCXwxz1X0miPr0pOsiEvilZE0zHWOXnyjXajbJtbalGDG0hiS6awvnb1PzCC3kXbYe807JFYEHtMXicnhi1Q";
        String ip = "0:0:0:0:0:0:0:1";

        Token entity = new Token(
            jwtToken,
            ip,
            true
        );

        assertEquals(0, entity.getId());
        assertEquals(jwtToken, entity.getToken());
        assertEquals(ip, entity.getIp());
        assertTrue(entity.isValid());
    }

    @Test
    void instantiateEmptyEntity(){

        Token entity = new Token();

        assertEquals(0, entity.getId());
        assertNull(entity.getIp());
        assertNull(entity.getToken());
        assertFalse(entity.isValid());
    }

    @Test
    void fillEmptyEntity(){
        int id = 5;
        String jwtToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYWNrIiwiY29tcGFueUlkIjoxLCJleHAiOjE2MDc3NjY2MDMsImlhdCI6MTYwNzc2MzAwMywidXNlcklkIjoxfQ.-1TCXwxz1X0miPr0pOsiEvilZE0zHWOXnyjXajbJtbalGDG0hiS6awvnb1PzCC3kXbYe807JFYEHtMXicnhi1Q";
        String ip = "0:0:0:0:0:0:0:1";

        Token entity = new Token();

        entity.setId(id);
        entity.setToken(jwtToken);
        entity.setIp(ip);
        entity.setValid(true);

        assertEquals(id, entity.getId());
        assertEquals(jwtToken, entity.getToken());
        assertEquals(ip, entity.getIp());
        assertTrue(entity.isValid());
    }

}
