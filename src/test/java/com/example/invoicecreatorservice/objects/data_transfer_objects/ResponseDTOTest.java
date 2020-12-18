package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResponseDTOTest {

    @Test
    void instantiateMessageEntity(){
        boolean status = true;
        String message = "responce message";

        ResponseDTO DTOentity = new ResponseDTO(status, message);

        assertEquals(status, DTOentity.getStatus());
        assertEquals(message, DTOentity.getMessage());
    }

    @Test
    void instantiateObjectEntity(){
        boolean status = true;
        Product entity = new Product(5, "toothpast", 13.6, 5, "hlth002",1);;

        ResponseDTO DTOentity = new ResponseDTO(status, entity);

        assertEquals(status, DTOentity.getStatus());
        assertNotNull(DTOentity.getBody());
    }

    @Test
    void instantiateEmptyEntity(){

        ResponseDTO DTOentity = new ResponseDTO();

        assertFalse(DTOentity.getStatus());
        assertNull(DTOentity.getMessage());
        assertNull(DTOentity.getBody());
    }

    @Test
    void fillEmptyEntity(){
        boolean status = true;
        Product entity = new Product(5, "toothpast", 13.6, 5, "hlth002",1);;
        String message = "responce message";

        ResponseDTO DTOentity = new ResponseDTO();

        DTOentity.setStatus(status);
        DTOentity.setBody(entity);
        DTOentity.setMessage(message);

        assertNotNull(DTOentity.getBody());
        assertTrue(DTOentity.getStatus());
        assertEquals(message, DTOentity.getMessage());
    }
}
