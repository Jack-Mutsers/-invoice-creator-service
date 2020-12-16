package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.User;
import com.example.invoicecreatorservice.objects.models.UserAccount;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserAccountForAlterationDTOTest {

    private final User userEntity = new User(2, "henk", "testlane 64", "1234 AB", "Testvile");
    private final UserAccount userAccountEntity = new UserAccount(2, true, "henk", "Password1!",2, userEntity, "123456", 1, "ROLE_OWNER");

    @Test
    void instantiateEntity(){
        UserForAlterationDTO userDTO = new UserForAlterationDTO(
            userEntity.getId(),
            userEntity.getName(),
            userEntity.getAddress(),
            userEntity.getZipcode(),
            userEntity.getCity()
        );

        UserAccountForAlterationDTO DTOentity = new UserAccountForAlterationDTO(
            userAccountEntity.getId(),
            userAccountEntity.isActive(),
            userAccountEntity.getUsername(),
            userAccountEntity.getPassword(),
            userDTO,
            userAccountEntity.getContactCode(),
            userAccountEntity.getRole(),
            userAccountEntity.getCompanyId()
        );

        assertEquals(userAccountEntity.getId(), DTOentity.getId());
        assertTrue(DTOentity.getActive());
        assertEquals(userAccountEntity.getUsername(), DTOentity.getUsername());
        assertEquals(userAccountEntity.getPassword(), DTOentity.getPassword());
        assertEquals(userDTO, DTOentity.getUser());
        assertEquals(userAccountEntity.getContactCode(), DTOentity.getContactCode());
        assertEquals(userAccountEntity.getRole(), DTOentity.getRole());
        assertEquals(userAccountEntity.getCompanyId(), DTOentity.getCompanyId());
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
    void setVariablesTest(){
        UserForAlterationDTO userDTO = new UserForAlterationDTO(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getAddress(),
                userEntity.getZipcode(),
                userEntity.getCity()
        );

        UserAccountForAlterationDTO DTOentity = new UserAccountForAlterationDTO();
        DTOentity.setId(userAccountEntity.getId());
        DTOentity.setUser(userDTO);

        assertEquals(userAccountEntity.getId(), DTOentity.getId());
        assertNotNull(DTOentity.getUser());
    }

    @Test
    void generateContactCodeTest(){
        UserForAlterationDTO userDTO = new UserForAlterationDTO(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getAddress(),
                userEntity.getZipcode(),
                userEntity.getCity()
        );

        UserAccountForAlterationDTO DTOentity = new UserAccountForAlterationDTO(
                userAccountEntity.getId(),
                userAccountEntity.isActive(),
                userAccountEntity.getUsername(),
                userAccountEntity.getPassword(),
                userDTO,
                null,
                userAccountEntity.getRole(),
                userAccountEntity.getCompanyId()
        );

        DTOentity.generateContactCode();

        assertNotNull(DTOentity.getContactCode());
    }

    @Test
    void entityValidationTestForUpdateSuccess(){

        UserForAlterationDTO userDTO = new UserForAlterationDTO(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getAddress(),
                userEntity.getZipcode(),
                userEntity.getCity()
        );

        UserAccountForAlterationDTO DTOentity = new UserAccountForAlterationDTO(
                userAccountEntity.getId(),
                userAccountEntity.isActive(),
                userAccountEntity.getUsername(),
                userAccountEntity.getPassword(),
                userDTO,
                userAccountEntity.getContactCode(),
                userAccountEntity.getRole(),
                userAccountEntity.getCompanyId()
        );

        assertFalse(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureOne(){

        UserForAlterationDTO userDTO = new UserForAlterationDTO(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getAddress(),
                userEntity.getZipcode(),
                userEntity.getCity()
        );

        UserAccountForAlterationDTO DTOentity = new UserAccountForAlterationDTO(
                0,
                userAccountEntity.isActive(),
                userAccountEntity.getUsername(),
                userAccountEntity.getPassword(),
                userDTO,
                userAccountEntity.getContactCode(),
                userAccountEntity.getRole(),
                userAccountEntity.getCompanyId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureTwo(){

        UserForAlterationDTO userDTO = new UserForAlterationDTO(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getAddress(),
                userEntity.getZipcode(),
                userEntity.getCity()
        );

        UserAccountForAlterationDTO DTOentity = new UserAccountForAlterationDTO(
                userAccountEntity.getId(),
                userAccountEntity.isActive(),
                null,
                userAccountEntity.getPassword(),
                userDTO,
                userAccountEntity.getContactCode(),
                userAccountEntity.getRole(),
                userAccountEntity.getCompanyId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureThree(){

        UserForAlterationDTO userDTO = new UserForAlterationDTO(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getAddress(),
                userEntity.getZipcode(),
                userEntity.getCity()
        );

        UserAccountForAlterationDTO DTOentity = new UserAccountForAlterationDTO(
                userAccountEntity.getId(),
                userAccountEntity.isActive(),
                userAccountEntity.getUsername(),
                userAccountEntity.getPassword(),
                userDTO,
                "   ",
                userAccountEntity.getRole(),
                userAccountEntity.getCompanyId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureFour(){

        UserForAlterationDTO userDTO = null;

        UserAccountForAlterationDTO DTOentity = new UserAccountForAlterationDTO(
                userAccountEntity.getId(),
                userAccountEntity.isActive(),
                userAccountEntity.getUsername(),
                userAccountEntity.getPassword(),
                userDTO,
                userAccountEntity.getContactCode(),
                userAccountEntity.getRole(),
                userAccountEntity.getCompanyId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureFive(){

        UserForAlterationDTO userDTO = new UserForAlterationDTO(
                0,
                userEntity.getName(),
                userEntity.getAddress(),
                userEntity.getZipcode(),
                userEntity.getCity()
        );

        UserAccountForAlterationDTO DTOentity = new UserAccountForAlterationDTO(
                userAccountEntity.getId(),
                userAccountEntity.isActive(),
                userAccountEntity.getUsername(),
                userAccountEntity.getPassword(),
                userDTO,
                userAccountEntity.getContactCode(),
                userAccountEntity.getRole(),
                userAccountEntity.getCompanyId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureSix(){

        UserForAlterationDTO userDTO = new UserForAlterationDTO(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getAddress(),
                userEntity.getZipcode(),
                userEntity.getCity()
        );

        UserAccountForAlterationDTO DTOentity = new UserAccountForAlterationDTO(
                userAccountEntity.getId(),
                userAccountEntity.isActive(),
                userAccountEntity.getUsername(),
                userAccountEntity.getPassword(),
                userDTO,
                userAccountEntity.getContactCode(),
                null,
                userAccountEntity.getCompanyId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForCreationSuccess(){

        UserForAlterationDTO userDTO = new UserForAlterationDTO(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getAddress(),
                userEntity.getZipcode(),
                userEntity.getCity()
        );

        UserAccountForAlterationDTO DTOentity = new UserAccountForAlterationDTO(
                userAccountEntity.getId(),
                userAccountEntity.isActive(),
                userAccountEntity.getUsername(),
                userAccountEntity.getPassword(),
                userDTO,
                userAccountEntity.getContactCode(),
                userAccountEntity.getRole(),
                userAccountEntity.getCompanyId()
        );

        assertFalse(DTOentity.validateForCreation());
    }

    @Test
    void entityValidationTestForCreationFailureOne(){

        UserForAlterationDTO userDTO = new UserForAlterationDTO(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getAddress(),
                userEntity.getZipcode(),
                userEntity.getCity()
        );

        UserAccountForAlterationDTO DTOentity = new UserAccountForAlterationDTO(
                userAccountEntity.getId(),
                userAccountEntity.isActive(),
                null,
                userAccountEntity.getPassword(),
                userDTO,
                userAccountEntity.getContactCode(),
                userAccountEntity.getRole(),
                userAccountEntity.getCompanyId()
        );

        assertTrue(DTOentity.validateForCreation());
    }

    @Test
    void entityValidationTestForCreationFailureTwo(){

        UserForAlterationDTO userDTO = new UserForAlterationDTO(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getAddress(),
                userEntity.getZipcode(),
                userEntity.getCity()
        );

        UserAccountForAlterationDTO DTOentity = new UserAccountForAlterationDTO(
                userAccountEntity.getId(),
                userAccountEntity.isActive(),
                userAccountEntity.getUsername(),
                "  ",
                userDTO,
                userAccountEntity.getContactCode(),
                userAccountEntity.getRole(),
                userAccountEntity.getCompanyId()
        );

        assertTrue(DTOentity.validateForCreation());
    }

    @Test
    void entityValidationTestForCreationFailureThree(){

        UserAccountForAlterationDTO DTOentity = new UserAccountForAlterationDTO(
                userAccountEntity.getId(),
                userAccountEntity.isActive(),
                userAccountEntity.getUsername(),
                userAccountEntity.getPassword(),
                null,
                userAccountEntity.getContactCode(),
                userAccountEntity.getRole(),
                userAccountEntity.getCompanyId()
        );

        assertTrue(DTOentity.validateForCreation());
    }

    @Test
    void entityValidationTestForCreationFailureFour(){

        UserForAlterationDTO userDTO = new UserForAlterationDTO(
                userEntity.getId(),
                null,
                userEntity.getAddress(),
                userEntity.getZipcode(),
                userEntity.getCity()
        );

        UserAccountForAlterationDTO DTOentity = new UserAccountForAlterationDTO(
                userAccountEntity.getId(),
                userAccountEntity.isActive(),
                userAccountEntity.getUsername(),
                userAccountEntity.getPassword(),
                userDTO,
                userAccountEntity.getContactCode(),
                userAccountEntity.getRole(),
                userAccountEntity.getCompanyId()
        );

        assertTrue(DTOentity.validateForCreation());
    }

    @Test
    void entityValidationTestForLoginSuccess(){
        int id = 0;
        boolean active = true;
        String username = "henk";
        String password = "Password1!";
        String contactCode = null;
        int companyId = 0;
        UserForAlterationDTO userDTO = null;
        String role = "ROLE_OWNER";

        UserAccountForAlterationDTO DTOentity = new UserAccountForAlterationDTO(
                id,
                active,
                username,
                password,
                userDTO,
                contactCode,
                role,
                companyId
        );

        assertFalse(DTOentity.validateLoginData());
    }

    @Test
    void entityValidationTestForLoginFailure(){
        int id = 0;
        boolean active = true;
        String username = "henk";
        String password = "";
        String contactCode = null;
        int companyId = 0;
        UserForAlterationDTO userDTO = null;
        String role = "ROLE_OWNER";


        UserAccountForAlterationDTO DTOentity = new UserAccountForAlterationDTO(
                id,
                active,
                username,
                password,
                userDTO,
                contactCode,
                role,
                companyId
        );

        assertTrue(DTOentity.validateLoginData());
    }

}
