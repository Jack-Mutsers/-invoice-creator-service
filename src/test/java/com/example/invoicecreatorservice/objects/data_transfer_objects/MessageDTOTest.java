package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.Message;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MessageDTOTest {
    @Test
    void instantiateEntity(){
        int id = 5;
        int userId = 16;
        String contactCode = "1Dfr23AS2d";
        String messageBody = "this is a test message for a unit test";
        String type = "notification";
        Boolean done = false;

        Message entity = new Message(
                id,
                userId,
                contactCode,
                messageBody,
                type,
                done
        );

        MessageDTO DTOentity = new MessageDTO(entity);

        assertEquals(id, DTOentity.getId());
        assertEquals(userId, DTOentity.getUserId());
        assertEquals(contactCode, DTOentity.getContactCode());
        assertEquals(messageBody, DTOentity.getMessageBody());
        assertEquals(type, DTOentity.getType());
        assertEquals(done, DTOentity.getDone());
    }

    @Test
    void instantiateEmptyEntity(){
        int id = 0;
        int userId = 0;
        String contactCode = null;
        String messageBody = null;
        String type = null;
        Boolean done = null;

        MessageDTO DTOentity = new MessageDTO();

        assertEquals(id, DTOentity.getId());
        assertEquals(userId, DTOentity.getUserId());
        assertEquals(contactCode, DTOentity.getContactCode());
        assertEquals(messageBody, DTOentity.getMessageBody());
        assertEquals(type, DTOentity.getType());
        assertEquals(done, DTOentity.getDone());
    }

    @Test
    void fillEmptyEntity(){
        int id = 5;
        int userId = 16;
        String contactCode = "1Dfr23AS2d";
        String messageBody = "this is a test message for a unit test";
        String type = "notification";
        Boolean done = true;

        Message DTOentity = new Message();

        DTOentity.setId(id);
        DTOentity.setUserId(userId);
        DTOentity.setContactCode(contactCode);
        DTOentity.setMessageBody(messageBody);
        DTOentity.setType(type);
        DTOentity.setDone(done);

        assertEquals(id, DTOentity.getId());
        assertEquals(userId, DTOentity.getUserId());
        assertEquals(contactCode, DTOentity.getContactCode());
        assertEquals(messageBody, DTOentity.getMessageBody());
        assertEquals(type, DTOentity.getType());
//        assertEquals(done, DTOentity.getDone());
    }
}
