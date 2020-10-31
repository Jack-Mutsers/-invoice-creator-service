package com.example.invoicecreatorservice.repositories;

import com.example.invoicecreatorservice.models.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Integer> {
    Message findById(int id);
    Message findByUserId(int id);
    Message findByContactCode(String connectionCode);
}
