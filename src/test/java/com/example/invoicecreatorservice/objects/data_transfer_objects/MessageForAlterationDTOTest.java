package com.example.invoicecreatorservice.objects.data_transfer_objects;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MessageForAlterationDTOTest {
    @Test
    void instantiateEntity(){
        int id = 5;
        int userId = 16;
        String contactCode = "1Dfr23AS2d";
        String messageBody = "this is a test message for a unit test";
        String type = "notification";
        boolean done = false;

        MessageForAlterationDTO DTOentity = new MessageForAlterationDTO(
            id,
            userId,
            contactCode,
            messageBody,
            type,
            done
        );

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

        MessageForAlterationDTO DTOentity = new MessageForAlterationDTO();

        assertEquals(id, DTOentity.getId());
        assertEquals(userId, DTOentity.getUserId());
        assertEquals(contactCode, DTOentity.getContactCode());
        assertEquals(messageBody, DTOentity.getMessageBody());
        assertEquals(type, DTOentity.getType());
        assertEquals(done, DTOentity.getDone());
    }

    @Test
    void entityValidationTestForUpdateSuccess(){
        int id = 5;
        int userId = 16;
        String contactCode = "1Dfr23AS2d";
        String messageBody = "this is a test message for a unit test";
        String type = "notification";
        Boolean done = false;

        MessageForAlterationDTO DTOentity = new MessageForAlterationDTO(
                id,
                userId,
                contactCode,
                messageBody,
                type,
                done
        );


        assertFalse(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailure(){
        int id = 5;
        int userId = 16;
        String contactCode = "1Dfr23AS2d";
        String messageBody = "this is a test message for a unit test";
        String type = "";
        Boolean done = false;

        MessageForAlterationDTO DTOentity = new MessageForAlterationDTO(
                id,
                userId,
                contactCode,
                messageBody,
                type,
                done
        );


        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForCreationSuccess(){
        int id = 0;
        int userId = 16;
        String contactCode = "1Dfr23AS2d";
        String messageBody = "this is a test message for a unit test";
        String type = "notification";
        Boolean done = false;

        MessageForAlterationDTO DTOentity = new MessageForAlterationDTO(
                id,
                userId,
                contactCode,
                messageBody,
                type,
                done
        );


        assertFalse(DTOentity.validateForCreation());
    }

    @Test
    void entityValidationTestForCreationFailure(){
        int id = 0;
        int userId = 16;
        String contactCode = null;
        String messageBody = "this is a test message for a unit test";
        String type = "";
        Boolean done = false;

        MessageForAlterationDTO DTOentity = new MessageForAlterationDTO(
                id,
                userId,
                contactCode,
                messageBody,
                type,
                done
        );


        assertTrue(DTOentity.validateForCreation());
    }

}
