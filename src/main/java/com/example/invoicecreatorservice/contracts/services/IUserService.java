package com.example.invoicecreatorservice.contracts.services;

import com.example.invoicecreatorservice.objects.data_transfer_objects.UserDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.UserForAlterationDTO;

public interface IUserService {
    UserDTO getUser(int id);
    boolean deleteUser(int id);
    UserDTO createUser(UserForAlterationDTO userDTO);
    boolean updateUser(int userId, UserForAlterationDTO userDTO);
}
