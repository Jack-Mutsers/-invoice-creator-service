package com.example.invoicecreatorservice.objects.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class JwtUserDetailsTest {

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

        UserAccount userAccount = new UserAccount(
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

        JwtUserDetails entity = new JwtUserDetails(userAccount);

        assertEquals(id, entity.getId());
        assertEquals(username, entity.getUsername());
        assertEquals(password, entity.getPassword());
        assertTrue(entity.isEnabled());
        assertEquals(companyId, entity.getCompanyId());
        assertEquals(1, entity.getAuthorities().size());
        assertTrue(entity.getAuthorities().stream().findFirst().isPresent());
        assertEquals(role, entity.getAuthorities().stream().findFirst().get().toString());

        assertTrue(entity.isAccountNonExpired());
        assertTrue(entity.isAccountNonLocked());
        assertTrue(entity.isCredentialsNonExpired());
    }

}
