package com.example.invoicecreatorservice.models;

import com.example.invoicecreatorservice.data_transfer_objects.UserAccountForAlterationDTO;
import com.example.invoicecreatorservice.data_transfer_objects.UserForAlterationDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserAccountTest {
    @Test
    void instantiateEntity(){
        int id = 2;
        boolean active = true;
        String username = "henk";
        String password = "Password1!";
        int userId = 2;
        String contactCode = "123456";
        int companyId = 1;
        String role = "ROLE_OWNER";

        UserAccount entity = new UserAccount(
            id,
            active,
            username,
            password,
            userId,
            contactCode,
            companyId,
            role
        );

        assertEquals(id, entity.getId());
        assertEquals(username, entity.getUsername());
        assertEquals(password, entity.getPassword());
        assertEquals(userId, entity.getUserId());
        assertEquals(contactCode, entity.getContactCode());
    }


    @Test
    void instantiateEmptyEntity(){
        int id = 0;
        String username = null;
        String password = null;
        int userId = 0;

        UserAccount entity = new UserAccount();

        assertEquals(id, entity.getId());
        assertEquals(username, entity.getUsername());
        assertEquals(password, entity.getPassword());
        assertEquals(userId, entity.getUserId());
    }


    @Test
    void instantiateEntityByDTO(){
        int id = 2;
        boolean active = true;
        String username = "henk";
        String password = "Password1!";
        int userId = 2;
        String contactCode = "123456";
        int companyId = 1;
        String role = "ROLE_OWNER";

        UserForAlterationDTO userDto = new UserForAlterationDTO(
            userId,
            "henk",
            "testlane 64",
            "1234 AB",
            "Testvile"
        );

        UserAccountForAlterationDTO entityDTO = new UserAccountForAlterationDTO(
            id,
            active,
            username,
            password,
            userDto,
            contactCode,
            companyId,
            role
        );

        UserAccount entity = new UserAccount(entityDTO);

        assertEquals(id, entity.getId());
        assertEquals(username, entity.getUsername());
        assertEquals(password, entity.getPassword());
        assertEquals(userId, entity.getUserId());
    }

    @Test
    void fillEmptyEntity(){
        int id = 2;
        String username = "henk";
        String password = "Password1!";
        int userId = 2;
        String contactCode = "123456";
        int companyId = 1;
        String role = "ROLE_OWNER";

        UserAccount entity = new UserAccount();

        entity.setId(id);
        entity.setUsername(username);
        entity.setPassword(password);
        entity.setUserId(userId);
        entity.setContactCode(contactCode);
        entity.setCompanyId(companyId);
        entity.setRole(role);

        assertEquals(id, entity.getId());
        assertEquals(username, entity.getUsername());
        assertEquals(password, entity.getPassword());
        assertEquals(userId, entity.getUserId());
        assertEquals(contactCode, entity.getContactCode());
        assertEquals(companyId, entity.getCompanyId());
        assertEquals(role, entity.getRole());
    }
}
