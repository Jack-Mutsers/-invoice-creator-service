package com.example.invoicecreatorservice.data_transfer_objects;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserAccountForAlterationDTOTest {
    @Test
    void instantiateEntity(){
        int id = 2;
        String username = "henk";
        String password = "Password1!";
        String contactCode = "123456";
        int companyId = 1;

        int userId = 2;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "Testvile";


        UserForAlterationDTO userDTO = new UserForAlterationDTO(
            userId,
            name,
            address,
            zipcode,
            city
        );


        UserAccountForAlterationDTO DTOentity = new UserAccountForAlterationDTO(
            id,
            username,
            password,
            userDTO,
            contactCode,
            companyId
        );

        assertEquals(id, DTOentity.getId());
        assertEquals(username, DTOentity.getUsername());
        assertEquals(password, DTOentity.getPassword());
        assertEquals(userDTO, DTOentity.getUser());
        assertEquals(contactCode, DTOentity.getContactCode());
    }


    @Test
    void instantiateEmptyEntity(){
        int id = 0;
        String username = null;
        String password = null;
        String contactCode = null;
        UserForAlterationDTO userDTO = null;

        UserAccountForAlterationDTO DTOentity = new UserAccountForAlterationDTO();

        assertEquals(id, DTOentity.getId());
        assertEquals(username, DTOentity.getUsername());
        assertEquals(password, DTOentity.getPassword());
        assertEquals(userDTO, DTOentity.getUser());
        assertEquals(contactCode, DTOentity.getContactCode());
    }


    @Test
    void entityValidationTestForUpdateSuccess(){
        int id = 2;
        String username = "henk";
        String password = "Password1!";
        String contactCode = "123456";
        int companyId = 1;

        int userId = 2;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "Testvile";


        UserForAlterationDTO userDTO = new UserForAlterationDTO(
                userId,
                name,
                address,
                zipcode,
                city
        );


        UserAccountForAlterationDTO DTOentity = new UserAccountForAlterationDTO(
                id,
                username,
                password,
                userDTO,
                contactCode,
                companyId
        );

        assertFalse(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailure(){
        int id = 0;
        String username = "henk";
        String password = "Password1!";
        String contactCode = "";
        int companyId = 1;

        int userId = 2;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "Testvile";


        UserForAlterationDTO userDTO = new UserForAlterationDTO(
                userId,
                name,
                address,
                zipcode,
                city
        );


        UserAccountForAlterationDTO DTOentity = new UserAccountForAlterationDTO(
                id,
                username,
                password,
                userDTO,
                contactCode,
                companyId
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForCreationSuccess(){
        int id = 0;
        String username = "henk";
        String password = "Password1!";
        String contactCode = "123456";
        int companyId = 1;

        int userId = 0;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "Testvile";


        UserForAlterationDTO userDTO = new UserForAlterationDTO(
                userId,
                name,
                address,
                zipcode,
                city
        );


        UserAccountForAlterationDTO DTOentity = new UserAccountForAlterationDTO(
                id,
                username,
                password,
                userDTO,
                contactCode,
                companyId
        );

        assertFalse(DTOentity.validateForCreation());
    }

    @Test
    void entityValidationTestForCreationFailure(){
        int id = 0;
        String username = "henk";
        String password = "";
        String contactCode = "123456";
        int companyId = 1;

        int userId = 0;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "Testvile";


        UserForAlterationDTO userDTO = new UserForAlterationDTO(
                userId,
                name,
                address,
                zipcode,
                city
        );


        UserAccountForAlterationDTO DTOentity = new UserAccountForAlterationDTO(
                id,
                username,
                password,
                userDTO,
                contactCode,
                companyId
        );

        assertTrue(DTOentity.validateForCreation());
    }

    @Test
    void entityValidationTestForLoginSuccess(){
        int id = 0;
        String username = "henk";
        String password = "Password1!";
        String contactCode = null;
        int companyId = 0;
        UserForAlterationDTO userDTO = null;

        UserAccountForAlterationDTO DTOentity = new UserAccountForAlterationDTO(
                id,
                username,
                password,
                userDTO,
                contactCode,
                companyId
        );

        assertFalse(DTOentity.validateLoginData());
    }

    @Test
    void entityValidationTestForLoginFailure(){
        int id = 0;
        String username = "henk";
        String password = "";
        String contactCode = null;
        int companyId = 0;
        UserForAlterationDTO userDTO = null;


        UserAccountForAlterationDTO DTOentity = new UserAccountForAlterationDTO(
                id,
                username,
                password,
                userDTO,
                contactCode,
                companyId
        );

        assertTrue(DTOentity.validateLoginData());
    }

}
