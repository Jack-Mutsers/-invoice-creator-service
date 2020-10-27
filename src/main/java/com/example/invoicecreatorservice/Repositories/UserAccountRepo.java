package com.example.invoicecreatorservice.Repositories;

import com.example.invoicecreatorservice.Models.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepo extends CrudRepository<UserAccount, Integer> {
    UserAccount findById(int id);
    UserAccount findByUsername(String name);
//    Boolean deleteById(int id);
//    Boolean update(UserAccount object);
}
