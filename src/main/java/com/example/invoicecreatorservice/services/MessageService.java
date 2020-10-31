package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.data_transfer_objects.MessageForCreationDTO;
import com.example.invoicecreatorservice.data_transfer_objects.MessageForUpdateDTO;
import com.example.invoicecreatorservice.models.Message;
import com.example.invoicecreatorservice.repositories.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    public Iterable<Message> getNotifications(int userId) {
        return null;
    }

    public ResponseEntity<Object> getOutgoingRequests(int userId) {
        return null;
    }

    public ResponseEntity<Object> getIncomingRequests(int userId) {
        return null;
    }

    public ResponseEntity<Object> createMessage(MessageForCreationDTO messageDTO) {
        return null;
    }

    public Boolean UpdateRequest(MessageForUpdateDTO messageDTO){
        return true;
    }
}
