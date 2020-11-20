package com.example.invoicecreatorservice.repositories;

import com.example.invoicecreatorservice.models.UserAccount;
import com.example.invoicecreatorservice.models.UserSession;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserSessionsRepo extends CrudRepository<UserSession, Integer> {
    UserSession findById(int id);
    UserSession findByToken(UUID id);
}
