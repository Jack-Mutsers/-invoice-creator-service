package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerForAlterationDTOTest {

    private final Customer entity = new Customer(5,"henk","testlane 64","1234 AB","Testvile",0,1);

    @Test
    void instantiateEntity(){

        CustomerForAlterationDTO DTOentity = new CustomerForAlterationDTO(
            entity.getId(),
            entity.getName(),
            entity.getAddress(),
            entity.getZipcode(),
            entity.getCity(),
            entity.getUserId(),
            entity.getCompanyId()
        );

        assertEquals(entity.getId(), DTOentity.getId());
        assertEquals(entity.getName(), DTOentity.getName());
        assertEquals(entity.getAddress(), DTOentity.getAddress());
        assertEquals(entity.getZipcode(), DTOentity.getZipcode());
        assertEquals(entity.getCity(), DTOentity.getCity());
        assertEquals(entity.getUserId(), DTOentity.getUserId());
        assertEquals(entity.getCompanyId(), DTOentity.getCompanyId());
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
    void setVariablesTest(){
        CustomerForAlterationDTO DTOentity = new CustomerForAlterationDTO();

        DTOentity.setId(entity.getId());
        DTOentity.setCompanyId(entity.getCompanyId());

        assertEquals(entity.getId(), DTOentity.getId());
        assertEquals(entity.getCompanyId(), DTOentity.getCompanyId());
    }

    @Test
    void entityValidationTestForUpdateSuccess(){

        CustomerForAlterationDTO DTOentity = new CustomerForAlterationDTO(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getZipcode(),
                entity.getCity(),
                entity.getUserId(),
                entity.getCompanyId()
        );

        assertFalse(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureOne(){

        CustomerForAlterationDTO DTOentity = new CustomerForAlterationDTO(
                0,
                entity.getName(),
                entity.getAddress(),
                entity.getZipcode(),
                entity.getCity(),
                entity.getUserId(),
                entity.getCompanyId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureTwo(){

        CustomerForAlterationDTO DTOentity = new CustomerForAlterationDTO(
                entity.getId(),
                null,
                entity.getAddress(),
                entity.getZipcode(),
                entity.getCity(),
                entity.getUserId(),
                entity.getCompanyId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureThree(){

        CustomerForAlterationDTO DTOentity = new CustomerForAlterationDTO(
                entity.getId(),
                entity.getName(),
                "",
                entity.getZipcode(),
                entity.getCity(),
                entity.getUserId(),
                entity.getCompanyId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureFour(){

        CustomerForAlterationDTO DTOentity = new CustomerForAlterationDTO(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                " ",
                entity.getCity(),
                entity.getUserId(),
                entity.getCompanyId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureFive(){

        CustomerForAlterationDTO DTOentity = new CustomerForAlterationDTO(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getZipcode(),
                "   ",
                entity.getUserId(),
                entity.getCompanyId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureSix(){

        CustomerForAlterationDTO DTOentity = new CustomerForAlterationDTO(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getZipcode(),
                entity.getCity(),
                entity.getUserId(),
                0
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForCreationSuccess(){

        CustomerForAlterationDTO DTOentity = new CustomerForAlterationDTO(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getZipcode(),
                entity.getCity(),
                entity.getUserId(),
                entity.getCompanyId()
        );

        assertFalse(DTOentity.validateForCreation());
    }

}
