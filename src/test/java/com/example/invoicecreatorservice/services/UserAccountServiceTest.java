package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.objects.data_transfer_objects.UserAccountDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.UserAccountForAlterationDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.UserDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.UserForAlterationDTO;
import com.example.invoicecreatorservice.objects.models.User;
import com.example.invoicecreatorservice.objects.models.UserAccount;
import com.example.invoicecreatorservice.repositories.UserAccountRepo;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserAccountServiceTest {
    @Mock
    UserAccountRepo repo;

    @InjectMocks
    UserAccountService service;

    private final List<UserAccount> entityList = new ArrayList<>();

    private UserAccountServiceTest(){
        entityList.add(new UserAccount(1, true, "henk", "$2a$10$vXNmL91UI1nOIuzMrUe.HONLO.Imwl/xaiIn.HDY8P.JWKnejOA6u", 1, new User(1, "Jhon Doe", "kerkstraat", "5926 DF", "Asten"),"123456", 1, "ROLE_OWNER"));
        entityList.add(new UserAccount(2, true, "karel", "$2a$10$vXNmL91UI1nOIuzMrUe.HONLO.Imwl/xaiIn.HDY8P.JWKnejOA6u", 2, new User(2, "Arnold Schwarzenegger", "titanusstraat", "6943 RC", "Geldrop"),"234561", 0, "ROLE_USER"));
        entityList.add(new UserAccount(3, true, "jan", "$2a$10$vXNmL91UI1nOIuzMrUe.HONLO.Imwl/xaiIn.HDY8P.JWKnejOA6u", 3, new User(3, "Tommy Blinder", "zonnenbloemlaan", "3496 PL", "heeze"),"345612", 1, "ROLE_OWNER"));
        entityList.add(new UserAccount(4, true, "bernard", "$2a$10$vXNmL91UI1nOIuzMrUe.HONLO.Imwl/xaiIn.HDY8P.JWKnejOA6u", 4, new User(4, "jhon snow", "st.martinlaan", "8512 BM", "Eindhoven"),"456123", 0, "ROLE_USER"));
        entityList.add(new UserAccount(5, true, "jacob", "$2a$10$vXNmL91UI1nOIuzMrUe.HONLO.Imwl/xaiIn.HDY8P.JWKnejOA6u", 5, new User(5, "henk jansen", "testlane 64", "1234 AB", "Testvile"),"561234", 1, "ROLE_OWNER"));
    }

    @Test
    void getUserAccountTest(){
        //Arrange
        UserAccount account = entityList.get(1);
        int id = account.getId();

        //Prepare overwrites
        when(repo.findById(id)).thenReturn(account);

        //Act
        UserAccountDTO accountDTO = service.getUserAccount(id);

        //Assert
        assertEquals(id, accountDTO.getId());
        assertNotNull(accountDTO.getUsername());
        assertEquals(account.getUserId(), accountDTO.getUserId());
        assertEquals(account.getCompanyId(), accountDTO.getCompanyId());
        assertNotNull(account.getRole(), accountDTO.getRole());
        assertNotNull(accountDTO.getContactCode());
        assertNotNull(accountDTO.getUser());
    }

    @Test
    void getEmployeesTest(){
        //Arrange
        List<UserAccount> accountList = new ArrayList<>();
        accountList.add(entityList.get(0));
        accountList.add(entityList.get(2));
        accountList.add(entityList.get(4));
        int companyId = 1;

        //Prepare overwrites
        when(repo.findAllByCompanyId(companyId)).thenReturn(accountList);

        //Act
        List<UserDTO> userDTOList = Lists.newArrayList(service.getEmployees(companyId));

        //Assert
        assertEquals(3, userDTOList.size());
        assertEquals(accountList.get(0).getUser().getId(), userDTOList.get(0).getId());
        assertEquals(accountList.get(1).getUser().getId(), userDTOList.get(1).getId());
        assertEquals(accountList.get(2).getUser().getId(), userDTOList.get(2).getId());
    }

    @Test
    void getEmployeesNullTest(){
        //Arrange
        int companyId = 0;

        //Prepare overwrites
        when(repo.findAllByCompanyId(companyId)).thenReturn(null);

        //Act
        List<UserDTO> userDTOList = Lists.newArrayList(service.getEmployees(companyId));

        //Assert
        assertEquals(0, userDTOList.size());
    }

    @Test
    void deleteUserTest(){
        //Arrange
        UserAccount account = entityList.get(0);
        int id = 1;
        String password = "Password1!";

        //Prepare overwrites
        when(repo.findById(id)).thenReturn(account);

        //Act
        boolean result = service.deleteUser(id, password);

        //Assert
        assertTrue(result);
    }

    @Test
    void deleteUserNullTest(){
        //Arrange
        int id = 1;
        String password = "Password1!";

        //Prepare overwrites
        when(repo.findById(id)).thenReturn(null);

        //Act
        boolean result = service.deleteUser(id, password);

        //Assert
        assertFalse(result);
    }

    @Test
    void deleteUserInvalidTest(){
        //Arrange
        UserAccount account = entityList.get(0);
        int id = 1;
        String password = "Password2!";

        //Prepare overwrites
        when(repo.findById(id)).thenReturn(account);

        //Act
        boolean result = service.deleteUser(id, password);

        //Assert
        assertFalse(result);
    }

    @Test
    void deleteUserExceptionTest(){
        //Arrange
        UserAccount account = entityList.get(0);
        int id = 1;
        String password = "Password1!";

        //Prepare overwrites
        doThrow(new EmptyResultDataAccessException(20)).when(repo).findById(id);

        //Act
        boolean result = service.deleteUser(id, password);

        //Assert
        assertFalse(result);
    }

    @Test
    void removeAllEmployeesTest(){
        //Arrange
        List<UserAccount> accountList = new ArrayList<>();
        accountList.add(entityList.get(0));
        accountList.add(entityList.get(2));
        accountList.add(entityList.get(4));
        int companyId = 1;

        //Prepare overwrites
        when(repo.findAllByCompanyId(companyId)).thenReturn(accountList);

        //Act
        boolean result = service.removeAllEmployees(companyId);

        //Assert
        assertTrue(result);
    }

    @Test
    void removeAllEmployeesNullTest(){
        //Arrange
        int companyId = 1;

        //Prepare overwrites
        when(repo.findAllByCompanyId(companyId)).thenReturn(null);

        //Act
        boolean result = service.removeAllEmployees(companyId);

        //Assert
        assertTrue(result);
    }

    @Test
    void removeAllEmployeesExceptionTest(){
        //Arrange
        int companyId = 1;

        //Prepare overwrites
        doThrow(new EmptyResultDataAccessException(20)).when(repo).findAllByCompanyId(companyId);

        //Act
        boolean result = service.removeAllEmployees(companyId);

        //Assert
        assertFalse(result);
    }

    @Test
    void removeAllEmployeeTest(){
        //Arrange
        UserAccount account = entityList.get(0);
        int userId = account.getUserId();
        int companyId = account.getCompanyId();

        //Prepare overwrites
        when(repo.findByUserIdAndCompanyId(userId, companyId)).thenReturn(account);

        //Act
        boolean result = service.removeEmployee(userId, companyId);

        //Assert
        assertTrue(result);
    }

    @Test
    void removeAllEmployeeNullTest(){
        //Arrange
        int userId = 1;
        int companyId = 0;

        //Prepare overwrites
        when(repo.findByUserIdAndCompanyId(userId, companyId)).thenReturn(null);

        //Act
        boolean result = service.removeEmployee(userId, companyId);

        //Assert
        assertFalse(result);
    }

    @Test
    void removeAllEmployeeExceptionTest(){
        //Arrange
        int userId = 1;
        int companyId = 0;

        //Prepare overwrites
        doThrow(new EmptyResultDataAccessException(20)).when(repo).findByUserIdAndCompanyId(userId, companyId);

        //Act
        boolean result = service.removeEmployee(userId, companyId);

        //Assert
        assertFalse(result);
    }

    @Test
    void createUserAccountTest(){
        //Arrange
        UserAccount account = entityList.get(2);
        UserAccountForAlterationDTO alterationDTO = new UserAccountForAlterationDTO(
            account.getId(),
            account.isActive(),
            account.getUsername(),
            account.getPassword(),
            new UserForAlterationDTO(
                account.getUser().getId(),
                account.getUser().getName(),
                account.getUser().getAddress(),
                account.getUser().getZipcode(),
                account.getUser().getCity()
            ),
            account.getContactCode(),
            account.getRole(),
            account.getCompanyId()
        );
        int userId = account.getUserId();

        //Prepare overwrites
        when(repo.save((UserAccount)notNull())).thenReturn(account);

        //Act
        UserAccountDTO accountDTO = service.createUserAccount(alterationDTO, userId);

        //Assert
        assertNotNull(accountDTO);
        assertEquals(account.getId(), accountDTO.getId());
        assertNotNull(accountDTO.getUsername());
        assertEquals(account.getUserId(), accountDTO.getUserId());
        assertEquals(account.getCompanyId(), accountDTO.getCompanyId());
        assertNotNull(account.getRole(), accountDTO.getRole());
        assertNotNull(accountDTO.getContactCode());
        assertNotNull(accountDTO.getUser());
    }

    @Test
    void createUserAccountExceptionTest(){
        //Arrange
        UserAccount account = entityList.get(2);
        UserAccountForAlterationDTO alterationDTO = new UserAccountForAlterationDTO(
                account.getId(),
                account.isActive(),
                account.getUsername(),
                account.getPassword(),
                new UserForAlterationDTO(
                        account.getUser().getId(),
                        account.getUser().getName(),
                        account.getUser().getAddress(),
                        account.getUser().getZipcode(),
                        account.getUser().getCity()
                ),
                account.getContactCode(),
                account.getRole(),
                account.getCompanyId()
        );
        int userId = account.getUserId();

        //Prepare overwrites
        doThrow(new EmptyResultDataAccessException(20)).when(repo).save((UserAccount)notNull());

        //Act
        UserAccountDTO accountDTO = service.createUserAccount(alterationDTO, userId);

        //Assert
        assertNull(accountDTO);
    }

    @Test
    void updateUserAccountUserTest(){
        //Arrange
        UserAccount account = entityList.get(2);
        UserAccountForAlterationDTO alterationDTO = new UserAccountForAlterationDTO(
                account.getId(),
                account.isActive(),
                account.getUsername(),
                null,
                new UserForAlterationDTO(
                        account.getUser().getId(),
                        account.getUser().getName(),
                        account.getUser().getAddress(),
                        account.getUser().getZipcode(),
                        account.getUser().getCity()
                ),
                account.getContactCode(),
                account.getRole(),
                account.getCompanyId()
        );
        int id = account.getId();

        //Prepare overwrites
        when(repo.findById(id)).thenReturn(account);

        //Act
        boolean result = service.updateUserAccount(alterationDTO);

        //Assert
        assertTrue(result);
    }

    @Test
    void updateUserAccountPasswordTest(){
        //Arrange
        UserAccount account = entityList.get(2);
        UserAccountForAlterationDTO alterationDTO = new UserAccountForAlterationDTO(
                account.getId(),
                account.isActive(),
                account.getUsername(),
                account.getPassword(),
                new UserForAlterationDTO(
                        account.getUser().getId(),
                        account.getUser().getName(),
                        account.getUser().getAddress(),
                        account.getUser().getZipcode(),
                        account.getUser().getCity()
                ),
                account.getContactCode(),
                account.getRole(),
                account.getCompanyId()
        );
        int id = account.getId();

        //Prepare overwrites
        when(repo.findById(id)).thenReturn(account);

        //Act
        boolean result = service.updateUserAccount(alterationDTO);

        //Assert
        assertTrue(result);
    }

    @Test
    void updateUserAccountNullTest(){
        //Arrange
        UserAccount account = entityList.get(2);
        UserAccountForAlterationDTO alterationDTO = new UserAccountForAlterationDTO(
                0,
                account.isActive(),
                account.getUsername(),
                account.getPassword(),
                new UserForAlterationDTO(
                        account.getUser().getId(),
                        account.getUser().getName(),
                        account.getUser().getAddress(),
                        account.getUser().getZipcode(),
                        account.getUser().getCity()
                ),
                account.getContactCode(),
                account.getRole(),
                account.getCompanyId()
        );
        int id = alterationDTO.getId();

        //Prepare overwrites
        when(repo.findById(id)).thenReturn(null);

        //Act
        boolean result = service.updateUserAccount(alterationDTO);

        //Assert
        assertFalse(result);
    }

    @Test
    void updateUserAccountExceptionTest(){
        //Arrange
        UserAccount account = entityList.get(2);
        UserAccountForAlterationDTO alterationDTO = new UserAccountForAlterationDTO(
                account.getId(),
                account.isActive(),
                account.getUsername(),
                account.getPassword(),
                new UserForAlterationDTO(
                        account.getUser().getId(),
                        account.getUser().getName(),
                        account.getUser().getAddress(),
                        account.getUser().getZipcode(),
                        account.getUser().getCity()
                ),
                account.getContactCode(),
                account.getRole(),
                account.getCompanyId()
        );
        int id = account.getId();

        //Prepare overwrites
        doThrow(new EmptyResultDataAccessException(20)).when(repo).findById(id);

        //Act
        boolean result = service.updateUserAccount(alterationDTO);

        //Assert
        assertFalse(result);
    }

    @Test
    void validateUsernameTakenTest(){
        //Arrange
        UserAccount account = entityList.get(1);
        String username = account.getUsername();

        //Prepare overwrites
        when(repo.findByUsername(username)).thenReturn(account);

        //Act
        boolean result = service.validateUsername(username);

        //Assert
        assertFalse(result);
    }

    @Test
    void validateUsernameAvailableTest(){
        //Arrange
        UserAccount account = entityList.get(1);
        String username = account.getUsername();

        //Prepare overwrites
        when(repo.findByUsername(username)).thenReturn(null);

        //Act
        boolean result = service.validateUsername(username);

        //Assert
        assertTrue(result);
    }

    @Test
    void setCompanyOwnerTest(){
        //Arrange
        UserAccount account = entityList.get(1);
        int id = account.getId();
        int companyId = account.getCompanyId();

        //Prepare overwrites
        when(repo.findById(id)).thenReturn(account);

        //Act
        boolean result = service.setCompanyOwner(id, companyId);

        //Assert
        assertTrue(result);
    }

    @Test
    void setCompanyOwnerNullTest(){
        //Arrange
        UserAccount account = entityList.get(1);
        int id = 0;
        int companyId = account.getCompanyId();

        //Prepare overwrites
        when(repo.findById(id)).thenReturn(null);

        //Act
        boolean result = service.setCompanyOwner(id, companyId);

        //Assert
        assertFalse(result);
    }

    @Test
    void setCompanyOwnerExceptionTest(){
        //Arrange
        UserAccount account = entityList.get(1);
        int id = 0;
        int companyId = account.getCompanyId();

        //Prepare overwrites
        doThrow(new EmptyResultDataAccessException(20)).when(repo).findById(id);

        //Act
        boolean result = service.setCompanyOwner(id, companyId);

        //Assert
        assertFalse(result);
    }

    @Test
    void addNewEmployeeTest(){
        //Arrange
        UserAccount account = entityList.get(1);
        String contactCode = account.getContactCode();
        int companyId = account.getCompanyId();

        //Prepare overwrites
        when(repo.findByContactCode(contactCode)).thenReturn(account);

        //Act
        boolean result = service.addNewEmployee(contactCode, companyId);

        //Assert
        assertTrue(result);
    }

    @Test
    void addNewEmployeeNullTest(){
        //Arrange
        UserAccount account = entityList.get(1);
        String contactCode = "";
        int companyId = account.getCompanyId();

        //Prepare overwrites
        when(repo.findByContactCode(contactCode)).thenReturn(null);

        //Act
        boolean result = service.addNewEmployee(contactCode, companyId);

        //Assert
        assertFalse(result);
    }

    @Test
    void addNewEmployeeExceptionTest(){
        //Arrange
        UserAccount account = entityList.get(1);
        String contactCode = "";
        int companyId = account.getCompanyId();

        //Prepare overwrites
        doThrow(new EmptyResultDataAccessException(20)).when(repo).findByContactCode(contactCode);

        //Act
        boolean result = service.addNewEmployee(contactCode, companyId);

        //Assert
        assertFalse(result);
    }

}
