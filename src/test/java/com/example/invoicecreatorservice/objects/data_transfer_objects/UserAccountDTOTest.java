package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.User;
import com.example.invoicecreatorservice.objects.models.UserAccount;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserAccountDTOTest {
    @Test
    void instantiateEntity(){
        int id = 2;
        boolean active = true;
        String username = "henk";
        String password = "Password1!";
        int userId = 2;
        String contactCode = "123456";
        int companyId = 1;
        String role = "ROLE_EMPLOYEE";

        UserAccount entity = new UserAccount(
                id,
                active,
                username,
                password,
                userId,
                new User(),
                contactCode,
                companyId,
                role
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
        String contactCode = null;

        UserAccountDTO DTOentity = new UserAccountDTO();

        assertEquals(id, DTOentity.getId());
        assertEquals(username, DTOentity.getUsername());
        assertEquals(contactCode, DTOentity.getContactCode());
    }

    @Test
    void fillEmptyEntity(){
        int id = 2;
        String username = "henk";
        String contactCode = "123456";

        UserAccountDTO DTOentity = new UserAccountDTO();

        DTOentity.setId(id);
        DTOentity.setUsername(username);
        DTOentity.setContactCode(contactCode);

        assertEquals(id, DTOentity.getId());
        assertEquals(username, DTOentity.getUsername());
        assertEquals(contactCode, DTOentity.getContactCode());
    }
}
