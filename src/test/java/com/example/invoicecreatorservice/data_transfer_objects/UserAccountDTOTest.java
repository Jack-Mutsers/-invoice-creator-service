package com.example.invoicecreatorservice.data_transfer_objects;

import com.example.invoicecreatorservice.models.UserAccount;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserAccountDTOTest {
    @Test
    void instantiateEntity(){
        int id = 2;
        String username = "henk";
        String password = "Password1!";
        int userId = 2;
        String contactCode = "123456";
        int companyId = 1;

        UserAccount entity = new UserAccount(
                id,
                username,
                password,
                userId,
                contactCode,
                companyId
        );

        UserAccountDTO DTOentity = new UserAccountDTO(entity);

        assertEquals(id, DTOentity.getId());
        assertEquals(username, DTOentity.getUsername());
        assertEquals(contactCode, DTOentity.getContactCode());
    }


    @Test
    void instantiateEmptyEntity(){
        int id = 0;
        String username = null;
        String password = null;
        String contactCode = null;
        int userId = 0;

        UserAccountDTO DTOentity = new UserAccountDTO();

        assertEquals(id, DTOentity.getId());
        assertEquals(username, DTOentity.getUsername());
        assertEquals(contactCode, DTOentity.getContactCode());
    }

    @Test
    void fillEmptyEntity(){
        int id = 2;
        String username = "henk";
        String password = "Password1!";
        int userId = 2;
        String contactCode = "123456";
        int companyId = 1;

        UserAccountDTO DTOentity = new UserAccountDTO();

        DTOentity.setId(id);
        DTOentity.setUsername(username);
        DTOentity.setContactCode(contactCode);

        assertEquals(id, DTOentity.getId());
        assertEquals(username, DTOentity.getUsername());
        assertEquals(contactCode, DTOentity.getContactCode());
    }
}
