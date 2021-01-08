package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserForAlterationDTOTest {

    private final User entity = new User(2, "henk", "testlane 64", "1234 AB", "Testvile");

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

        UserForAlterationDTO DTOentity = new UserForAlterationDTO(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getZipcode(),
                entity.getCity()
        );

        assertFalse(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureOne(){

        UserForAlterationDTO DTOentity = new UserForAlterationDTO(
                0,
                entity.getName(),
                entity.getAddress(),
                entity.getZipcode(),
                entity.getCity()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureTwo(){

        UserForAlterationDTO DTOentity = new UserForAlterationDTO(
                entity.getId(),
                null,
                entity.getAddress(),
                entity.getZipcode(),
                entity.getCity()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureThree(){

        UserForAlterationDTO DTOentity = new UserForAlterationDTO(
                entity.getId(),
                entity.getName(),
                "",
                entity.getZipcode(),
                entity.getCity()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureFour(){

        UserForAlterationDTO DTOentity = new UserForAlterationDTO(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                "  ",
                entity.getCity()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureFive(){

        UserForAlterationDTO DTOentity = new UserForAlterationDTO(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getZipcode(),
                "     "
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

}
