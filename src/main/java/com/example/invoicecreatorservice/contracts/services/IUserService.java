package com.example.invoicecreatorservice.contracts.services;

import com.example.invoicecreatorservice.objects.data_transfer_objects.UserDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.UserForAlterationDTO;
import com.example.invoicecreatorservice.objects.models.User;

public interface IUserService {
    UserDTO getUser(int id);
    Iterable<User> getAllUser();
    boolean deleteUser(int id);
    UserDTO createUser(UserForAlterationDTO userDTO);
    boolean updateUser(int userId, UserForAlterationDTO userDTO);
}
