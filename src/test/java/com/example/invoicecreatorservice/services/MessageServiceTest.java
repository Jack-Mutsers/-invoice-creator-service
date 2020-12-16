package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.objects.data_transfer_objects.CustomerDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.CustomerForAlterationDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.MessageDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.MessageForAlterationDTO;
import com.example.invoicecreatorservice.objects.models.Customer;
import com.example.invoicecreatorservice.objects.models.Message;
import com.example.invoicecreatorservice.repositories.CustomerRepo;
import com.example.invoicecreatorservice.repositories.MessageRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
class MessageServiceTest {

    @Mock
    MessageRepo repo;

    @InjectMocks
    MessageService service;

    private final List<Message> entityNotificationList = new ArrayList<>();
    private final List<Message> entityRequestList = new ArrayList<>();

    private MessageServiceTest(){
        entityNotificationList.add(new Message(1, 1, "dE21df8eDF", "test message 1", "notification", false));
        entityNotificationList.add(new Message(2, 1, "86DfrS0dFe", "test message 4", "notification", false));
        entityNotificationList.add(new Message(3, 5, "dE21df8eDF", "test message 5", "notification", false));
        entityRequestList.add(new Message(4, 1, "Fd25dsaDF9", "test message 2", "request", false));
        entityRequestList.add(new Message(5, 2, "3a566FSE2d", "test message 3", "request", false));
        entityRequestList.add(new Message(6, 1, "86DfrS0dFe", "test message 3", "request", false));
    }

    @Test
    void getNotificationsTestSuccess(){
        //Arrange
        Message entity = entityNotificationList.get(0);

        List<MessageDTO> entityList = new ArrayList<>();
        entityList.add(new MessageDTO(entityNotificationList.get(0)));
        entityList.add(new MessageDTO(entityNotificationList.get(2)));


        //Prepare overwrites
        when(repo.findByContactCodeAndTypeAndDone(entity.getContactCode(), "notification", false)).thenReturn(entityList);

        //Act
        List<MessageDTO> resultEntity = (List) service.getMessagesForMe(entity.getContactCode(), entity.getType());

        //Assert
        assertEquals(resultEntity.size(), entityList.size());
    }

    @Test
    void getNotificationsTestFailure(){
        //Arrange
        Message entity = entityNotificationList.get(0);
        List<MessageDTO> entityList = new ArrayList<>();

        //Prepare overwrites
        when(repo.findByContactCodeAndTypeAndDone(entity.getContactCode(), "notification", false)).thenReturn(entityList);

        //Act
        List<MessageDTO> resultEntity = (List) service.getMessagesForMe(entity.getContactCode(), entity.getType());

        //Assert
        assertEquals(0, resultEntity.size());
    }

    @Test
    void getOutgoingRequestsTestSuccess(){
        //Arrange
        Message entity = entityRequestList.get(0);

        List<MessageDTO> entityList = new ArrayList<>();
        entityList.add(new MessageDTO(entityRequestList.get(0)));
        entityList.add(new MessageDTO(entityRequestList.get(2)));

        //Prepare overwrites
        when(repo.findByUserIdAndTypeAndDone(entity.getUserId(), "request", false)).thenReturn(entityList);

        //Act
        List<MessageDTO> resultEntity = (List) service.getOutgoingRequests(entity.getUserId(), entity.getType());

        //Assert
        assertEquals(resultEntity.size(), entityList.size());
    }

    @Test
    void getOutgoingRequestsTestFailure(){
        //Arrange
        Message entity = entityRequestList.get(0);

        List<MessageDTO> entityList = new ArrayList<>();

        //Prepare overwrites
        when(repo.findByContactCodeAndTypeAndDone(entity.getContactCode(), "request", false)).thenReturn(entityList);

        //Act
        List<MessageDTO> resultEntity = (List) service.getOutgoingRequests(entity.getUserId(), entity.getType());

        //Assert
        assertEquals(0, resultEntity.size());
    }

    @Test
    void getIncomingRequestsTestSuccess(){
        //Arrange
        Message entity = entityRequestList.get(1);

        List<MessageDTO> entityList = new ArrayList<>();
        entityList.add(new MessageDTO(entityRequestList.get(1)));

        //Prepare overwrites
        when(repo.findByContactCodeAndTypeAndDone(entity.getContactCode(), "request", false)).thenReturn(entityList);

        //Act
        List<MessageDTO> resultEntity = (List) service.getMessagesForMe(entity.getContactCode(), entity.getType());

        //Assert
        assertEquals(resultEntity.size(), entityList.size());
    }

    @Test
    void getIncomingRequestsTestFailure(){
        //Arrange
        Message entity = entityRequestList.get(1);

        List<MessageDTO> entityList = new ArrayList<>();

        //Prepare overwrites
        when(repo.findByContactCodeAndTypeAndDone(entity.getContactCode(), "request", false)).thenReturn(entityList);

        //Act
        List<MessageDTO> resultEntity = (List) service.getMessagesForMe(entity.getContactCode(), entity.getType());

        //Assert
        assertEquals(0, resultEntity.size());
    }

    @Test
    void createMessageTestSuccess(){
        //Arrange
        Message entity = entityRequestList.get(1);
        MessageForAlterationDTO entityDTO = new MessageForAlterationDTO(
                0,
                entity.getUserId(),
                entity.getContactCode(),
                entity.getMessageBody(),
                entity.getType(),
                entity.getDone()
        );

        //Prepare overwrites
        when(repo.save((Message) notNull())).thenReturn(entity);

        //Act
        boolean resultEntity = service.createMessage(entityDTO);

        //Assert
        assertTrue(resultEntity);
    }

    @Test
    void createMessageTestExceptionFailure(){
        //Arrange

        //Prepare overwrites
        when(repo.save((Message)isNull())).thenThrow(new IllegalArgumentException("Target object must not be null"));

        //Act
        boolean resultEntity = service.createMessage(null);

        //Assert
        assertFalse(resultEntity);
    }

    @Test
    void UpdateRequestTestSuccess(){
        //Arrange
        Message entity = entityRequestList.get(0);
        MessageForAlterationDTO entityDTO = new MessageForAlterationDTO(
                4,
                entity.getUserId(),
                entity.getContactCode(),
                entity.getMessageBody(),
                entity.getType(),
                true
        );

        //Prepare overwrites
        when(repo.save((Message) notNull())).thenReturn(entity);

        //Act
        boolean result = service.updateRequest(entityDTO);

        //Assert
        assertTrue(result);
    }

    @Test
    void UpdateRequestTestFailure(){
        //Arrange

        //Prepare overwrites
        when(repo.save((Message) notNull())).thenThrow(new NullPointerException("zipcode cannot be empty"));

        //Act
        boolean result = service.updateRequest(null);

        //Assert
        assertFalse(result);
    }
}
