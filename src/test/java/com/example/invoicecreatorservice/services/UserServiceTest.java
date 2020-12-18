package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.objects.data_transfer_objects.UserDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.UserForAlterationDTO;
import com.example.invoicecreatorservice.objects.models.User;
import com.example.invoicecreatorservice.repositories.UserRepo;
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
class UserServiceTest {
    @Mock
    UserRepo repo;

    @InjectMocks
    UserService service;

    private final List<User> entityList = new ArrayList<>();

    private UserServiceTest(){
        entityList.add(new User(1, "Jhon Doe", "kerkstraat", "5926 DF", "Asten"));
        entityList.add(new User(2, "Arnold Schwarzenegger", "titanusstraat", "6943 RC", "Geldrop"));
        entityList.add(new User(3, "Tommy Blinder", "zonnenbloemlaan", "3496 PL", "heeze"));
        entityList.add(new User(4, "jhon snow", "st.martinlaan", "8512 BM", "Eindhoven"));
        entityList.add(new User(5, "henk jansen", "testlane 64", "1234 AB", "Testvile"));
    }

    @Test
    void getUserTest(){
        //Arrange
        User user = entityList.get(4);
        int id = user.getId();

        //Prepare overwrites
        when(repo.findById(id)).thenReturn(user);

        //Act
        UserDTO userDTO = service.getUser(id);

        //Assert
        assertNotNull(userDTO);
        assertEquals(user.getId(), userDTO.getId());
        assertNotNull(userDTO.getName());
        assertNotNull(userDTO.getAddress());
        assertNotNull(userDTO.getZipcode());
        assertNotNull(userDTO.getCity());
    }

    @Test
    void getUserNullTest(){
        //Arrange
        int id = 0;

        //Prepare overwrites
        when(repo.findById(id)).thenReturn(null);

        //Act
        UserDTO userDTO = service.getUser(id);

        //Assert
        assertNotNull(userDTO);
        assertEquals(0, userDTO.getId());
        assertNull(userDTO.getName());
        assertNull(userDTO.getAddress());
        assertNull(userDTO.getZipcode());
        assertNull(userDTO.getCity());
    }

    @Test
    void deleteUserTest(){
        //Arrange
        User user = entityList.get(4);
        int id = user.getId();

        //Prepare overwrites

        //Act
        boolean result = service.deleteUser(id);

        //Assert
        assertTrue(result);
    }

    @Test
    void deleteUserExceptionTest(){
        //Arrange
        int id = 0;

        //Prepare overwrites
        doThrow(new EmptyResultDataAccessException(1000)).when(repo).deleteById(id);

        //Act
        boolean result = service.deleteUser(id);

        //Assert
        assertFalse(result);
    }

    @Test
    void createUserTest(){
        //Arrange
        User user = entityList.get(4);
        UserForAlterationDTO alterationDTO = new UserForAlterationDTO(
          user.getId(),
          user.getName(),
          user.getAddress(),
          user.getZipcode(),
          user.getCity()
        );

        //Prepare overwrites
        when(repo.save((User)notNull())).thenReturn(user);

        //Act
        UserDTO userDTO = service.createUser(alterationDTO);

        //Assert
        assertNotNull(userDTO);
        assertEquals(user.getId(), userDTO.getId());
        assertNotNull(userDTO.getName());
        assertNotNull(userDTO.getAddress());
        assertNotNull(userDTO.getZipcode());
        assertNotNull(userDTO.getCity());
    }

    @Test
    void createUserExceptionTest(){
        //Arrange
        User user = entityList.get(4);
        UserForAlterationDTO alterationDTO = new UserForAlterationDTO(
                user.getId(),
                user.getName(),
                user.getAddress(),
                user.getZipcode(),
                user.getCity()
        );

        //Prepare overwrites
        doThrow(new EmptyResultDataAccessException(1000)).when(repo).save((User)notNull());

        //Act
        UserDTO userDTO = service.createUser(alterationDTO);

        //Assert
        assertNull(userDTO);
    }

    @Test
    void updateUserTest(){
        //Arrange
        User user = entityList.get(4);
        UserForAlterationDTO alterationDTO = new UserForAlterationDTO(
                user.getId(),
                user.getName(),
                user.getAddress(),
                user.getZipcode(),
                user.getCity()
        );
        int userId = user.getId();

        //Prepare overwrites
        when(repo.save((User)notNull())).thenReturn(user);

        //Act
        boolean result = service.updateUser(userId, alterationDTO);

        //Assert
        assertTrue(result);
    }

    @Test
    void updateUserExceptionTest(){
        //Arrange
        User user = entityList.get(4);
        UserForAlterationDTO alterationDTO = new UserForAlterationDTO(
                user.getId(),
                user.getName(),
                user.getAddress(),
                user.getZipcode(),
                user.getCity()
        );
        int userId = user.getId();

        //Prepare overwrites
        doThrow(new EmptyResultDataAccessException(1000)).when(repo).save((User)notNull());

        //Act
        boolean result = service.updateUser(userId, alterationDTO);

        //Assert
        assertFalse(result);
    }

}
