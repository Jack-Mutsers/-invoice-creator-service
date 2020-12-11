package com.example.invoicecreatorservice.contracts.services;

import com.example.invoicecreatorservice.objects.data_transfer_objects.UserAccountDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.UserAccountForAlterationDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.UserDTO;

public interface IUserAccountService {
    UserAccountDTO getUserAccount(int id);
    Iterable<UserDTO> getEmployees(int companyId);
    boolean deleteUser(int id, String password);
    boolean removeAllEmployees(int companyId);
    UserAccountDTO createUserAccount(UserAccountForAlterationDTO accountDTO, int userId);
    boolean updateUserAccount(UserAccountForAlterationDTO accountDTO);
    boolean validateUsername(String username);
    boolean addCompanyToUser(int id, int companyId);
}
