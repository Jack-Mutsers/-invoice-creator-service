package com.example.invoicecreatorservice.contracts.services;

import com.example.invoicecreatorservice.objects.data_transfer_objects.MessageDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.MessageForAlterationDTO;

public interface IMessageService {
    Iterable<MessageDTO> getNotifications(String contactCode);
    Iterable<MessageDTO> getOutgoingRequests(int userId);
    Iterable<MessageDTO> getIncomingRequests(String contactCode);
    boolean createMessage(MessageForAlterationDTO messageDTO);
    boolean updateRequest(MessageForAlterationDTO messageDTO);
}
