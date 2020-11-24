package com.example.invoicecreatorservice.repositories;

import com.example.invoicecreatorservice.objects.models.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepo extends CrudRepository<UserAccount, Integer> {
    UserAccount findById(int id);
    UserAccount findByUsername(String name);
    UserAccount findByUsernameAndActive(String name, boolean active);
}
