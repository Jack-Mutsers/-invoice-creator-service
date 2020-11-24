package com.example.invoicecreatorservice.repositories;

import com.example.invoicecreatorservice.objects.data_transfer_objects.MessageDTO;
import com.example.invoicecreatorservice.objects.models.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Integer> {
    Message findById(int id);
    Message findByUserId(int id);
    Message findByContactCode(String connectionCode);
    List<MessageDTO> findByContactCodeAndTypeAndDone(String connectionCode, String type, boolean done);
    List<MessageDTO> findByUserIdAndTypeAndDone(int userId, String type, boolean done);
}
