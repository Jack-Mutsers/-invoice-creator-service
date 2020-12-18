package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.objects.models.JwtUserDetails;
import com.example.invoicecreatorservice.objects.models.User;
import com.example.invoicecreatorservice.objects.models.UserAccount;
import com.example.invoicecreatorservice.repositories.UserAccountRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
class JwtUserDetailsServiceTest {
    @Mock
    UserAccountRepo repo;

    @InjectMocks
    JwtUserDetailsService service;

    private final List<UserAccount> entityList = new ArrayList<>();

    private JwtUserDetailsServiceTest(){
        entityList.add(new UserAccount(1, true, "henk", "Password1!", 1, new User(),"123456", 1, "ROLE_OWNER"));
        entityList.add(new UserAccount(2, true, "karel", "Password1!", 2, new User(),"234561", 0, "ROLE_USER"));
        entityList.add(new UserAccount(3, true, "jan", "Password1!", 3, new User(),"345612", 1, "ROLE_OWNER"));
        entityList.add(new UserAccount(4, true, "bernard", "Password1!", 4, new User(),"456123", 0, "ROLE_USER"));
        entityList.add(new UserAccount(5, true, "jacob", "Password1!", 5, new User(),"561234", 1, "ROLE_OWNER"));
    }

    @Test
    void loadUserByUsernameTest(){
        //Arrange
        UserAccount account = entityList.get(2);
        String username = account.getUsername();

        //Prepare overwrites
        when(repo.findByUsernameAndActive(username, true)).thenReturn(account);

        //Act
        JwtUserDetails result = service.loadUserByUsername(username);

        //Assert
        assertNotNull(result);
        assertEquals(account.getId(), result.getId());
        assertEquals(username, result.getUsername());
        assertEquals(account.getPassword(), result.getPassword());
        assertTrue(result.isEnabled());
        assertEquals(account.getCompanyId(), result.getCompanyId());
        assertEquals(1, result.getAuthorities().size());
        assertTrue(result.getAuthorities().stream().findFirst().isPresent());
        assertEquals(account.getRole(), result.getAuthorities().stream().findFirst().get().toString());

        assertTrue(result.isAccountNonExpired());
        assertTrue(result.isAccountNonLocked());
        assertTrue(result.isCredentialsNonExpired());
    }

    @Test
    void loadUserByUsernameNullExceptionTest(){
        //Arrange
        String username = "test";

        //Prepare overwrites
        when(repo.findByUsernameAndActive(username, true)).thenReturn(null);

        //Act
        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
            service.loadUserByUsername(username);
        });

        String expectedMessage = "Not found: " + username;
        String actualMessage = exception.getMessage();

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void loadUserByUsernameMethodExceptionTest(){
        //Arrange
        String username = "test";

        //Prepare overwrites
        doThrow(new UsernameNotFoundException("Not found: " + username)).when(repo).deleteById(notNull());

        //Act
        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
            service.loadUserByUsername(username);
        });

        String expectedMessage = "Not found: " + username;
        String actualMessage = exception.getMessage();

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
