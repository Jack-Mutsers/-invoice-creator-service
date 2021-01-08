package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.contracts.services.IMessageService;
import com.example.invoicecreatorservice.helpers.logger.LoggerService;
import com.example.invoicecreatorservice.objects.data_transfer_objects.MessageDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.MessageForAlterationDTO;
import com.example.invoicecreatorservice.objects.models.Message;
import com.example.invoicecreatorservice.repositories.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService implements IMessageService {
    @Autowired
    private MessageRepo messageRepo;

    public Iterable<MessageDTO> getMessagesForMe(String contactCode, String type) {
        return messageRepo.findByContactCodeAndTypeAndDone(contactCode, type, false);
    }

    public Iterable<MessageDTO> getOutgoingRequests(int userId, String type) {
        return messageRepo.findByUserIdAndTypeAndDone(userId, type, false);
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
            LoggerService.warn(ex.getMessage());
            return false;
        }
    }

    public boolean updateRequest(MessageForAlterationDTO messageDTO){
        try{
            Message message = new Message(messageDTO);

            messageRepo.save(message);

            return true;
        }catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return false;
        }
    }
}
