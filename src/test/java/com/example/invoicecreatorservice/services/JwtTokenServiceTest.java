package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.objects.models.Token;
import com.example.invoicecreatorservice.repositories.TokenRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class JwtTokenServiceTest {
    @Mock
    TokenRepo repo;

    @InjectMocks
    JwtTokenService service;

    private final List<Token> entityList = new ArrayList<>();

    private JwtTokenServiceTest(){
        entityList.add(new Token(1,"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYWNrIiwiY29tcGFueUlkIjoxLCJleHAiOjE2MDc3NjY2MDMsImlhdCI6MTYwNzc2MzAwMywidXNlcklkIjoxfQ.-1TCXwxz1X0miPr0pOsiEvilZE0zHWOXnyjXajbJtbalGDG0hiS6awvnb1PzCC3kXbYe807JFYEHtMXicnhi1Q","0:0:0:0:0:0:0:1",false));
        entityList.add(new Token(2,"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZW1vIiwiY29tcGFueUlkIjoxLCJleHAiOjE2MDc3NjYzNzYsImlhdCI6MTYwNzc2Mjc3NiwidXNlcklkIjoyNH0.Z4BvdeXNxFyTFqXgZdEZA7pKbxZCkA-pJI6wkFtXIjz0gRo0m5hDvB42K-ei94W7dfauJK46NbfOJ48Egjv3lg","0:0:0:0:0:0:0:1",true));
        entityList.add(new Token(3,"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYWNrIiwiY29tcGFueUlkIjoxLCJleHAiOjE2MDc3NjU5NDQsImlhdCI6MTYwNzc2MjM0NCwidXNlcklkIjoxfQ._mrqM6NMbyjvWmmoj02HcvFPmWgXKffCOgSMcyhhhCZs2eAo0NIQ2IOiDYrTR6D4DfEooxl9eZ1f7FWpgNn-gw","0:0:0:0:0:0:0:1",true));
        entityList.add(new Token(4,"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZW1vIiwiY29tcGFueUlkIjoxLCJleHAiOjE2MDc3NjU4MjksImlhdCI6MTYwNzc2MjIyOSwidXNlcklkIjoyNH0.-rcZ2AkUzGvtATswier5mkF7uOayEtfZ8Pqi52pFp7R4lYNl4xxQQoqYWqWxZBR-zAInYqTvwYLsQoGYvqj6Kg","0:0:0:0:0:0:0:1",false));
        entityList.add(new Token(5,"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYWNrIiwiY29tcGFueUlkIjoxLCJleHAiOjE2MDc3NjU1MDMsImlhdCI6MTYwNzc2MTkwMywidXNlcklkIjoxfQ.gD2GfV0kUHNW2XF-s9T6--XUtFlw1-DphYYV5OjA7lbKoNMcRSVpQxW_3yxqntyDvOaYUXHyUybZXxV9GE7SYQ","0:0:0:0:0:0:0:1",true));
    }

    @Test
    void validateTokenTrueTest(){
        //Arrange
        Token token = entityList.get(2);
        String jwtToken = token.getToken();
        String ip = token.getIp();

        //Prepare overwrites
        when(repo.findByJwtTokenAndIp(jwtToken, ip)).thenReturn(token);

        //Act
        boolean result = service.validateToken(jwtToken, ip);

        //Assert
        assertTrue(result);
    }

    @Test
    void validateTokenNullTest(){
        //Arrange
        String jwtToken = "";
        String ip = entityList.get(2).getIp();

        //Prepare overwrites
        when(repo.findByJwtTokenAndIp(jwtToken, ip)).thenReturn(null);

        //Act
        boolean result = service.validateToken(jwtToken, ip);

        //Assert
        assertFalse(result);
    }

    @Test
    void validateTokenInvalidTest(){
        //Arrange
        Token token = entityList.get(0);
        String jwtToken = token.getToken();
        String ip = token.getIp();

        //Prepare overwrites
        when(repo.findByJwtTokenAndIp(jwtToken, ip)).thenReturn(token);

        //Act
        boolean result = service.validateToken(jwtToken, ip);

        //Assert
        assertFalse(result);
    }

    @Test
    void addNewTokenTest(){
        //Arrange
        Token token = entityList.get(2);
        String jwtToken = token.getToken();
        String ip = token.getIp();

        //Prepare overwrites
        when(repo.save((Token)notNull())).thenReturn(token);

        //Act
        boolean result = service.addNewToken(jwtToken, ip);

        //Assert
        assertTrue(result);
    }

    @Test
    void addNewTokenNoTokenTest(){
        //Arrange
        Token token = entityList.get(2);
        String jwtToken = "  ";
        String ip = token.getIp();

        //Prepare overwrites
        when(repo.save((Token)notNull())).thenReturn(token);

        //Act
        boolean result = service.addNewToken(jwtToken, ip);

        //Assert
        assertFalse(result);
    }

    @Test
    void addNewTokenNoIpTest(){
        //Arrange
        Token token = entityList.get(2);
        String jwtToken = token.getToken();
        String ip = null;

        //Prepare overwrites
        when(repo.save((Token)notNull())).thenReturn(token);

        //Act
        boolean result = service.addNewToken(jwtToken, ip);

        //Assert
        assertFalse(result);
    }

    @Test
    void setExpiredTokenTest(){
        //Arrange
        Token token = entityList.get(2);
        String jwtToken = token.getToken();
        String ip = token.getIp();

        //Prepare overwrites
        when(repo.findByJwtTokenAndIp(jwtToken, ip)).thenReturn(token);
        when(repo.save((Token)notNull())).thenReturn(token);

        //Act
        boolean result = service.setExpiredToken(jwtToken, ip);

        //Assert
        assertTrue(result);
    }

    @Test
    void setExpiredTokenNoTokenTest(){
        //Arrange
        Token token = entityList.get(2);
        String jwtToken = "";
        String ip = token.getIp();

        //Prepare overwrites
        when(repo.findByJwtTokenAndIp(jwtToken, ip)).thenReturn(token);
        when(repo.save((Token)notNull())).thenReturn(token);

        //Act
        boolean result = service.setExpiredToken(jwtToken, ip);

        //Assert
        assertFalse(result);
    }

    @Test
    void setExpiredTokenNoIpTest(){
        //Arrange
        Token token = entityList.get(2);
        String jwtToken = token.getToken();
        String ip = null;

        //Prepare overwrites
        when(repo.findByJwtTokenAndIp(jwtToken, ip)).thenReturn(token);
        when(repo.save((Token)notNull())).thenReturn(token);

        //Act
        boolean result = service.setExpiredToken(jwtToken, ip);

        //Assert
        assertFalse(result);
    }
}
