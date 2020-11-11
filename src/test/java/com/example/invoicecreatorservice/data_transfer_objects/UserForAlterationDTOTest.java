package com.example.invoicecreatorservice.data_transfer_objects;

import com.example.invoicecreatorservice.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserForAlterationDTOTest {

    @Test
    void instantiateEntity(){
        int id = 2;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "Testvile";

        UserForAlterationDTO DTOentity = new UserForAlterationDTO(
            id,
            name,
            address,
            zipcode,
            city
        );

        assertEquals(id, DTOentity.getId());
        assertEquals(name, DTOentity.getName());
        assertEquals(address, DTOentity.getAddress());
        assertEquals(zipcode, DTOentity.getZipcode());
        assertEquals(city, DTOentity.getCity());
    }


    @Test
    void instantiateEmptyEntity(){
        int id = 0;
        String name = null;
        String address = null;
        String zipcode = null;
        String city = null;

        UserForAlterationDTO DTOentity = new UserForAlterationDTO();

        assertEquals(id, DTOentity.getId());
        assertEquals(name, DTOentity.getName());
        assertEquals(address, DTOentity.getAddress());
        assertEquals(zipcode, DTOentity.getZipcode());
        assertEquals(city, DTOentity.getCity());
    }

    @Test
    void entityValidationTestForUpdateSuccess(){
        int id = 2;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "Testvile";

        UserForAlterationDTO DTOentity = new UserForAlterationDTO(
                id,
                name,
                address,
                zipcode,
                city
        );

        assertFalse(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailure(){
        int id = 0;
        String name = "henk";
        String address = "";
        String zipcode = "1234 AB";
        String city = "Testvile";

        UserForAlterationDTO DTOentity = new UserForAlterationDTO(
                id,
                name,
                address,
                zipcode,
                city
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForCreationSuccess(){
        int id = 0;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "Testvile";

        UserForAlterationDTO DTOentity = new UserForAlterationDTO(
                id,
                name,
                address,
                zipcode,
                city
        );

        assertFalse(DTOentity.validateForCreation());
    }

    @Test
    void entityValidationTestForCreationFailure(){
        int id = 0;
        String name = "henk";
        String address = "";
        String zipcode = "1234 AB";
        String city = "Testvile";

        UserForAlterationDTO DTOentity = new UserForAlterationDTO(
                id,
                name,
                address,
                zipcode,
                city
        );

        assertTrue(DTOentity.validateForCreation());
    }

}
