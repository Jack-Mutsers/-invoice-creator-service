package com.example.invoicecreatorservice.objects.data_transfer_objects;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerForAlterationDTOTest {

    @Test
    void instantiateEntity(){
        int id = 5;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "Testvile";
        int userId = 0;
        int companyId = 1;

        CustomerForAlterationDTO DTOentity = new CustomerForAlterationDTO(
            id,
            name,
            address,
            zipcode,
            city,
            userId,
            companyId
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

        CustomerForAlterationDTO DTOentity = new CustomerForAlterationDTO();

        assertEquals(id, DTOentity.getId());
        assertEquals(name, DTOentity.getName());
        assertEquals(address, DTOentity.getAddress());
        assertEquals(zipcode, DTOentity.getZipcode());
        assertEquals(city, DTOentity.getCity());
    }

    @Test
    void entityValidationTestForUpdateSuccess(){
        int id = 5;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "Testvile";
        int userId = 0;
        int companyId = 1;

        CustomerForAlterationDTO DTOentity = new CustomerForAlterationDTO(
                id,
                name,
                address,
                zipcode,
                city,
                userId,
                companyId
        );

        assertFalse(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailure(){
        int id = 5;
        String name = "henk";
        String address = "";
        String zipcode = "1234 AB";
        String city = "Testvile";
        int userId = 0;
        int companyId = 1;

        CustomerForAlterationDTO DTOentity = new CustomerForAlterationDTO(
                id,
                name,
                address,
                zipcode,
                city,
                userId,
                companyId
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
        int userId = 0;
        int companyId = 1;

        CustomerForAlterationDTO DTOentity = new CustomerForAlterationDTO(
                id,
                name,
                address,
                zipcode,
                city,
                userId,
                companyId
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
        int userId = 0;
        int companyId = 1;

        CustomerForAlterationDTO DTOentity = new CustomerForAlterationDTO(
                id,
                name,
                address,
                zipcode,
                city,
                userId,
                companyId
        );

        assertTrue(DTOentity.validateForCreation());
    }

}
