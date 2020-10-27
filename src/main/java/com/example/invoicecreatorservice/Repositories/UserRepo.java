package com.example.invoicecreatorservice.Repositories;

import com.example.invoicecreatorservice.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {
    User findById(int id);
//    Boolean deleteById(int id);
//    Boolean update(User object);
}
