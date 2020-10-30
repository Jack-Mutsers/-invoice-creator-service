package com.example.invoicecreatorservice.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserAccountTest {
    @Test
    public void instantiateEntity(){
        int id = 2;
        String username = "henk";
        String password = "Password1!";
        int userId = 2;

        UserAccount userAccount = new UserAccount(
                id,
                username,
                password,
                userId
        );

        assertEquals(id, userAccount.getId());
        assertEquals(username, userAccount.getUsername());
        assertEquals(password, userAccount.getPassword());
        assertEquals(userId, userAccount.getUserId());
    }


    @Test
    public void instantiateEmptyEntity(){
        int id = 0;
        String username = null;
        String password = null;
        int userId = 0;

        UserAccount userAccount = new UserAccount();

        assertEquals(id, userAccount.getId());
        assertEquals(username, userAccount.getUsername());
        assertEquals(password, userAccount.getPassword());
        assertEquals(userId, userAccount.getUserId());
    }
}
