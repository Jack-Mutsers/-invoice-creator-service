package com.example.invoicecreatorservice.data_transfer_objects;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductCategoryForAlterationDTOTest {
    @Test
    void instantiateEntity(){
        int id = 5;
        String name = "healthcare";
        int btw = 23;

        ProductCategoryForAlterationDTO DTOentity = new ProductCategoryForAlterationDTO(
            id,
            name,
            btw
        );

        assertEquals(id, DTOentity.getId());
        assertEquals(name, DTOentity.getName());
        assertEquals(btw, DTOentity.getBtw());
    }

    @Test
    void instantiateEmptyEntity(){
        int id = 0;
        String name = null;
        int btw = 0;

        ProductCategoryForAlterationDTO DTOentity = new ProductCategoryForAlterationDTO();

        assertEquals(id, DTOentity.getId());
        assertEquals(name, DTOentity.getName());
        assertEquals(btw, DTOentity.getBtw());
    }

    @Test
    void entityValidationTestForUpdateSuccess(){
        int id = 5;
        String name = "healthcare";
        int btw = 23;

        ProductCategoryForAlterationDTO DTOentity = new ProductCategoryForAlterationDTO(
                id,
                name,
                btw
        );

        assertFalse(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailure(){
        int id = 0;
        String name = "healthcare";
        int btw = 23;

        ProductCategoryForAlterationDTO DTOentity = new ProductCategoryForAlterationDTO(
                id,
                name,
                btw
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForCreationSuccess(){
        int id = 0;
        String name = "healthcare";
        int btw = 23;

        ProductCategoryForAlterationDTO DTOentity = new ProductCategoryForAlterationDTO(
                id,
                name,
                btw
        );

        assertFalse(DTOentity.validateForCreation());
    }

    @Test
    void entityValidationTestForCreationFailure(){
        int id = 0;
        String name = "healthcare";
        int btw = 0;

        ProductCategoryForAlterationDTO DTOentity = new ProductCategoryForAlterationDTO(
                id,
                name,
                btw
        );

        assertTrue(DTOentity.validateForCreation());
    }

}
