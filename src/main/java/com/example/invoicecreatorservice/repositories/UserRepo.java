package com.example.invoicecreatorservice.repositories;

import com.example.invoicecreatorservice.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {
    User findById(int id);
}
