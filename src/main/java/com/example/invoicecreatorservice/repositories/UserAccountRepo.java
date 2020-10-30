package com.example.invoicecreatorservice.repositories;

import com.example.invoicecreatorservice.models.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepo extends CrudRepository<UserAccount, Integer> {
    UserAccount findById(int id);
    UserAccount findByUsername(String name);
}
