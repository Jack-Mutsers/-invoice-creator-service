package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.Company;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompanyForAlterationDTOTest {

    private final Company entity = new Company(5, "henk", "testlane 64", "1234 AB", "Testvile", "1Dfr23AS2d", "0612345678", 16);

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

        CompanyForAlterationDTO entity = new CompanyForAlterationDTO();

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
    void FillEmptyEntityTest(){
        int id = 5;
        int ownerId = 16;

        CompanyForAlterationDTO entity = new CompanyForAlterationDTO();
        entity.setId(id);
        entity.setOwnerId(ownerId);

        assertEquals(id, entity.getId());
        assertEquals(ownerId, entity.getOwnerId());
    }

    @Test
    void entityValidationTestForUpdateSuccess(){
        CompanyForAlterationDTO DTOentity = new CompanyForAlterationDTO(
                this.entity.getId(),
                this.entity.getName(),
                this.entity.getAddress(),
                this.entity.getZipcode(),
                this.entity.getCity(),
                this.entity.getTelephoneNumber(),
                this.entity.getContactCode(),
                this.entity.getOwnerId()
        );

        assertFalse(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureOne(){

        CompanyForAlterationDTO DTOentity = new CompanyForAlterationDTO(
                0,
                this.entity.getName(),
                this.entity.getAddress(),
                this.entity.getZipcode(),
                this.entity.getCity(),
                this.entity.getTelephoneNumber(),
                this.entity.getContactCode(),
                this.entity.getOwnerId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureTwo(){

        CompanyForAlterationDTO DTOentity = new CompanyForAlterationDTO(
                this.entity.getId(),
                null,
                this.entity.getAddress(),
                this.entity.getZipcode(),
                this.entity.getCity(),
                this.entity.getTelephoneNumber(),
                this.entity.getContactCode(),
                this.entity.getOwnerId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureThree(){

        CompanyForAlterationDTO DTOentity = new CompanyForAlterationDTO(
                this.entity.getId(),
                this.entity.getName(),
                "",
                this.entity.getZipcode(),
                this.entity.getCity(),
                this.entity.getTelephoneNumber(),
                this.entity.getContactCode(),
                this.entity.getOwnerId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureFour(){

        CompanyForAlterationDTO DTOentity = new CompanyForAlterationDTO(
                this.entity.getId(),
                this.entity.getName(),
                this.entity.getAddress(),
                "  ",
                this.entity.getCity(),
                this.entity.getTelephoneNumber(),
                this.entity.getContactCode(),
                this.entity.getOwnerId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureFive(){

        CompanyForAlterationDTO DTOentity = new CompanyForAlterationDTO(
                this.entity.getId(),
                this.entity.getName(),
                this.entity.getAddress(),
                this.entity.getZipcode(),
                null,
                this.entity.getTelephoneNumber(),
                this.entity.getContactCode(),
                this.entity.getOwnerId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureSix(){

        CompanyForAlterationDTO DTOentity = new CompanyForAlterationDTO(
                this.entity.getId(),
                this.entity.getName(),
                this.entity.getAddress(),
                this.entity.getZipcode(),
                this.entity.getCity(),
                null,
                this.entity.getContactCode(),
                this.entity.getOwnerId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureSeven(){

        CompanyForAlterationDTO DTOentity = new CompanyForAlterationDTO(
                this.entity.getId(),
                this.entity.getName(),
                this.entity.getAddress(),
                this.entity.getZipcode(),
                this.entity.getCity(),
                this.entity.getTelephoneNumber(),
                null,
                this.entity.getOwnerId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureEight(){

        CompanyForAlterationDTO DTOentity = new CompanyForAlterationDTO(
                this.entity.getId(),
                this.entity.getName(),
                this.entity.getAddress(),
                this.entity.getZipcode(),
                this.entity.getCity(),
                this.entity.getTelephoneNumber(),
                this.entity.getContactCode(),
                0
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForCreationSuccess(){

        CompanyForAlterationDTO DTOentity = new CompanyForAlterationDTO(
                entity.getName(),
                entity.getAddress(),
                entity.getZipcode(),
                entity.getCity(),
                entity.getTelephoneNumber(),
                entity.getOwnerId()
        );

        assertFalse(DTOentity.validateForCreation());
    }

}
