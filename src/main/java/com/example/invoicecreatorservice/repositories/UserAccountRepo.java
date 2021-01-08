package com.example.invoicecreatorservice.repositories;

import com.example.invoicecreatorservice.objects.models.UserAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserAccountRepo extends CrudRepository<UserAccount, Integer> {
    UserAccount findById(int id);
    UserAccount findByContactCode(String contactCode);
    UserAccount findByUsername(String username);
    UserAccount findByUsernameAndActive(String name, boolean active);
    UserAccount findByUserIdAndCompanyId(int id, int companyId);
    List<UserAccount> findAllByCompanyId(int companyId);
}
