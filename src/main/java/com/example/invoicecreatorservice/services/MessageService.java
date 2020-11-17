package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.data_transfer_objects.MessageDTO;
import com.example.invoicecreatorservice.data_transfer_objects.MessageForAlterationDTO;
import com.example.invoicecreatorservice.models.Message;
import com.example.invoicecreatorservice.repositories.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    public Iterable<MessageDTO> getNotifications(String contactCode) {
        String type = "notification";

        List<MessageDTO> messages = messageRepo.findByContactCodeAndTypeAndDone(contactCode, type, false);

        if(messages.isEmpty()){ return null; }

        return messages;
    }

    public Iterable<MessageDTO> getOutgoingRequests(int userId) {
        String type = "request";

        List<MessageDTO> messages = messageRepo.findByUserIdAndTypeAndDone(userId, type, false);

        if(messages.isEmpty()){ return null; }

        return messages;
    }

    public Iterable<MessageDTO> getIncomingRequests(String contactCode) {
        String type = "request";

        List<MessageDTO> messages = messageRepo.findByContactCodeAndTypeAndDone(contactCode, type, false);

        if(messages.isEmpty()){ return null; }

        return messages;
    }

    public boolean createMessage(MessageForAlterationDTO messageDTO) {
        try{
            Message message = new Message(messageDTO);
            message.setDone(false);

            // set id to 0 to prevent update of existing record on create
            message.setId(0);

            messageRepo.save(message);

            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public Boolean updateRequest(MessageForAlterationDTO messageDTO){
        try{
            Message message = new Message(messageDTO);

            messageRepo.save(message);

            return true;
        }catch (Exception ex){
            return false;
        }
    }
}
