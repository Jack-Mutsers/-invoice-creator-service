package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.User;
import com.example.invoicecreatorservice.objects.models.UserAccount;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        assertEquals(userId, DTOentity.getUserId());
        assertEquals(companyId, DTOentity.getCompanyId());
        assertEquals(role, DTOentity.getRole());
        assertEquals(contactCode, DTOentity.getContactCode());
        assertNotNull(DTOentity.getUser());
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
        int userId = 2;
        String contactCode = "123456";
        int companyId = 1;
        String role = "ROLE_EMPLOYEE";
        String token = "test";

        UserAccountDTO DTOentity = new UserAccountDTO();

        DTOentity.setId(id);
        DTOentity.setUsername(username);
        DTOentity.setUserId(userId);
        DTOentity.setUser(new UserDTO());
        DTOentity.setContactCode(contactCode);
        DTOentity.setCompanyId(companyId);
        DTOentity.setCompany(new CompanyDTO());
        DTOentity.setRole(role);
        DTOentity.setToken(token);

        assertEquals(id, DTOentity.getId());
        assertEquals(username, DTOentity.getUsername());
        assertEquals(userId, DTOentity.getUserId());
        assertEquals(contactCode, DTOentity.getContactCode());
        assertEquals(companyId, DTOentity.getCompanyId());
        assertEquals(role, DTOentity.getRole());
        assertEquals(token, DTOentity.getToken());
        assertNotNull(DTOentity.getUser());
        assertNotNull(DTOentity.getCompany());

    }
}
