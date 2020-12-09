package com.example.invoicecreatorservice.contracts.services;

import com.example.invoicecreatorservice.objects.data_transfer_objects.UserAccountDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.UserAccountForAlterationDTO;

public interface IUserAccountService {
    UserAccountDTO getUserAccount(int id);
    boolean deleteUser(int id, String password);
    UserAccountDTO createUserAccount(UserAccountForAlterationDTO accountDTO, int userId);
    boolean updateUserAccount(UserAccountForAlterationDTO accountDTO);
    boolean validateUsername(String username);
    boolean addCompanyToUser(int id, int companyId);
}
