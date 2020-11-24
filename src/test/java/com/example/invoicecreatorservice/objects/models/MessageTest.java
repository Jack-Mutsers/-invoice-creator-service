package com.example.invoicecreatorservice.objects.models;

import com.example.invoicecreatorservice.objects.data_transfer_objects.MessageForAlterationDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MessageTest {
    @Test
    void instantiateEntity(){
        int id = 5;
        int userId = 16;
        String contactCode = "1Dfr23AS2d";
        String messageBody = "this is a test message for a unit test";
        String type = "notification";
        boolean done = false;

        Message entity = new Message(
            id,
            userId,
            contactCode,
            messageBody,
            type,
            done
        );

        assertEquals(id, entity.getId());
        assertEquals(userId, entity.getUserId());
        assertEquals(contactCode, entity.getContactCode());
        assertEquals(messageBody, entity.getMessageBody());
        assertEquals(type, entity.getType());
        assertEquals(done, entity.getDone());
    }

    @Test
    void instantiateEmptyEntity(){
        int id = 0;
        int userId = 0;
        String contactCode = null;
        String messageBody = null;
        String type = null;
        boolean done = false;

        Message entity = new Message();

        assertEquals(id, entity.getId());
        assertEquals(userId, entity.getUserId());
        assertEquals(contactCode, entity.getContactCode());
        assertEquals(messageBody, entity.getMessageBody());
        assertEquals(type, entity.getType());
        assertEquals(done, entity.getDone());
    }

    @Test
    void instantiateEntityByDTO(){
        int id = 5;
        int userId = 16;
        String contactCode = "1Dfr23AS2d";
        String messageBody = "this is a test message for a unit test";
        String type = "notification";
        boolean done = false;

        MessageForAlterationDTO entityDTO = new MessageForAlterationDTO(
            id,
            userId,
            contactCode,
            messageBody,
            type,
            done
        );

        Message entity = new Message(entityDTO);

        assertEquals(id, entity.getId());
        assertEquals(userId, entity.getUserId());
        assertEquals(contactCode, entity.getContactCode());
        assertEquals(messageBody, entity.getMessageBody());
        assertEquals(type, entity.getType());
        assertEquals(done, entity.getDone());
    }

    @Test
    void fillEmptyEntity(){
        int id = 5;
        int userId = 16;
        String contactCode = "1Dfr23AS2d";
        String messageBody = "this is a test message for a unit test";
        String type = "notification";
        boolean done = false;

        Message entity = new Message();

        entity.setId(id);
        entity.setUserId(userId);
        entity.setContactCode(contactCode);
        entity.setMessageBody(messageBody);
        entity.setType(type);
        entity.setDone(done);

        assertEquals(id, entity.getId());
        assertEquals(userId, entity.getUserId());
        assertEquals(contactCode, entity.getContactCode());
        assertEquals(messageBody, entity.getMessageBody());
        assertEquals(type, entity.getType());
        assertEquals(done, entity.getDone());
    }
}
