package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.Message;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MessageForAlterationDTOTest {

    private final Message entity = new Message(5,16,"1Dfr23AS2d","this is a test message for a unit test","notification",false);

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

        MessageForAlterationDTO DTOentity = new MessageForAlterationDTO(
                entity.getId(),
                entity.getUserId(),
                entity.getContactCode(),
                entity.getMessageBody(),
                entity.getType(),
                entity.getDone()
        );

        boolean result = DTOentity.validateForUpdate();

        assertFalse(result);
    }

    @Test
    void entityValidationTestForUpdateFailureOne(){

        MessageForAlterationDTO DTOentity = new MessageForAlterationDTO(
                0,
                entity.getUserId(),
                entity.getContactCode(),
                entity.getMessageBody(),
                entity.getType(),
                entity.getDone()
        );

        boolean result = DTOentity.validateForUpdate();

        assertTrue(result);
    }

    @Test
    void entityValidationTestForUpdateFailureTwo(){

        MessageForAlterationDTO DTOentity = new MessageForAlterationDTO(
                entity.getId(),
                0,
                entity.getContactCode(),
                entity.getMessageBody(),
                entity.getType(),
                entity.getDone()
        );

        boolean result = DTOentity.validateForUpdate();

        assertTrue(result);
    }

    @Test
    void entityValidationTestForUpdateFailureThree(){

        MessageForAlterationDTO DTOentity = new MessageForAlterationDTO(
                entity.getId(),
                entity.getUserId(),
                null,
                entity.getMessageBody(),
                entity.getType(),
                entity.getDone()
        );

        boolean result = DTOentity.validateForUpdate();

        assertTrue(result);
    }

    @Test
    void entityValidationTestForUpdateFailureFour(){

        MessageForAlterationDTO DTOentity = new MessageForAlterationDTO(
                entity.getId(),
                entity.getUserId(),
                entity.getContactCode(),
                "  ",
                entity.getType(),
                entity.getDone()
        );

        boolean result = DTOentity.validateForUpdate();

        assertTrue(result);
    }

    @Test
    void entityValidationTestForUpdateFailureFive(){

        MessageForAlterationDTO DTOentity = new MessageForAlterationDTO(
                entity.getId(),
                entity.getUserId(),
                entity.getContactCode(),
                entity.getMessageBody(),
                "",
                entity.getDone()
        );

        boolean result = DTOentity.validateForUpdate();

        assertTrue(result);
    }

    @Test
    void entityValidationTestForCreationSuccess(){

        MessageForAlterationDTO DTOentity = new MessageForAlterationDTO(
                entity.getId(),
                entity.getUserId(),
                entity.getContactCode(),
                entity.getMessageBody(),
                entity.getType(),
                entity.getDone()
        );

        assertFalse(DTOentity.validateForCreation());
    }

    @Test
    void setIdTest(){
        int id = 5;

        MessageForAlterationDTO message = new MessageForAlterationDTO();
        message.setId(id);

        assertEquals(id, message.getId());
    }
}
