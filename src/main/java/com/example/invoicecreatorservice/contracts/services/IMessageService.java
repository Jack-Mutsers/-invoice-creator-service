package com.example.invoicecreatorservice.contracts.services;

import com.example.invoicecreatorservice.objects.data_transfer_objects.MessageDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.MessageForAlterationDTO;

public interface IMessageService {
    Iterable<MessageDTO> getMessagesForMe(String contactCode, String type);
    Iterable<MessageDTO> getOutgoingRequests(int userId, String type);
    boolean createMessage(MessageForAlterationDTO messageDTO);
    boolean updateRequest(MessageForAlterationDTO messageDTO);
}
