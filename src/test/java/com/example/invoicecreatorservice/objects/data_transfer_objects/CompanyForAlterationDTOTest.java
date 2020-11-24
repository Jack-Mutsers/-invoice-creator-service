package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.Company;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompanyForAlterationDTOTest {

    @Test
    void instantiateEntityForCreate(){
        int id = 0;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "Testvile";
        String contactCode = null;
        String telephoneNumber = "0612345678";

        int ownerId = 16;

        CompanyForAlterationDTO DTOentity = new CompanyForAlterationDTO(
            name,
            address,
            zipcode,
            city,
            telephoneNumber,
            ownerId
        );

        assertEquals(id, DTOentity.getId());
        assertEquals(name, DTOentity.getName());
        assertEquals(address, DTOentity.getAddress());
        assertEquals(zipcode, DTOentity.getZipcode());
        assertEquals(city, DTOentity.getCity());
        assertEquals(telephoneNumber, DTOentity.getTelephoneNumber());
        assertNotEquals(contactCode, DTOentity.getContactCode());
        assertEquals(ownerId, DTOentity.getOwnerId());
    }

    @Test
    void instantiateEntityForUpdate(){
        int id = 5;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "Testvile";
        String contactCode = "1Dfr23AS2d";
        String telephoneNumber = "0612345678";
        int ownerId = 16;

        CompanyForAlterationDTO DTOentity = new CompanyForAlterationDTO(
            id,
            name,
            address,
            zipcode,
            city,
            telephoneNumber,
            contactCode,
            ownerId
        );

        assertEquals(id, DTOentity.getId());
        assertEquals(name, DTOentity.getName());
        assertEquals(address, DTOentity.getAddress());
        assertEquals(zipcode, DTOentity.getZipcode());
        assertEquals(city, DTOentity.getCity());
        assertEquals(telephoneNumber, DTOentity.getTelephoneNumber());
        assertEquals(contactCode, DTOentity.getContactCode());
        assertEquals(ownerId, DTOentity.getOwnerId());
    }

    @Test
    void instantiateEmptyEntity(){
        int id = 0;
        String name = null;
        String address = null;
        String zipcode = null;
        String city = null;
        String telephoneNumber = null;
        String contactCode = null;
        int ownerId = 0;

        Company entity = new Company();

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(address, entity.getAddress());
        assertEquals(zipcode, entity.getZipcode());
        assertEquals(city, entity.getCity());
        assertEquals(telephoneNumber, entity.getTelephoneNumber());
        assertEquals(contactCode, entity.getContactCode());
        assertEquals(ownerId, entity.getOwnerId());
    }

    @Test
    void entityValidationTestForUpdateSuccess(){
        int id = 5;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "Testvile";
        String contactCode = "1Dfr23AS2d";
        String telephoneNumber = "0612345678";
        int ownerId = 16;

        CompanyForAlterationDTO DTOentity = new CompanyForAlterationDTO(
                id,
                name,
                address,
                zipcode,
                city,
                telephoneNumber,
                contactCode,
                ownerId
        );

        assertFalse(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailure(){
        int id = 0;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "";
        String contactCode = "1Dfr23AS2d";
        String telephoneNumber = "0612345678";
        int ownerId = 16;

        CompanyForAlterationDTO DTOentity = new CompanyForAlterationDTO(
                id,
                name,
                address,
                zipcode,
                city,
                telephoneNumber,
                contactCode,
                ownerId
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
        String contactCode = null;
        String telephoneNumber = "0612345678";

        int ownerId = 16;

        CompanyForAlterationDTO DTOentity = new CompanyForAlterationDTO(
                name,
                address,
                zipcode,
                city,
                telephoneNumber,
                ownerId
        );

        assertFalse(DTOentity.validateForCreation());
    }

    @Test
    void entityValidationTestForCreationFailure(){
        int id = 0;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = null;
        String city = "";
        String contactCode = null;
        String telephoneNumber = "0612345678";

        int ownerId = 16;

        CompanyForAlterationDTO DTOentity = new CompanyForAlterationDTO(
                name,
                address,
                zipcode,
                city,
                telephoneNumber,
                ownerId
        );

        assertTrue(DTOentity.validateForCreation());
    }

}
