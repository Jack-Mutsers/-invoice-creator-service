package com.example.invoicecreatorservice.objects.data_transfer_objects;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductForAlterationDTOTest {

    @Test
    void instantiateEntity(){
        int id = 5;
        String name = "toothpast";
        double price = 13.6;
        int priceResult = 1360;
        int categoryId = 5;
        String productCode = "hlth002";

        ProductForAlterationDTO DTOentity = new ProductForAlterationDTO(
            id,
            name,
            price,
            categoryId,
            productCode
        );

        assertEquals(id, DTOentity.getId());
        assertEquals(name, DTOentity.getName());
        assertEquals(priceResult, DTOentity.getPrice());
        assertEquals(categoryId, DTOentity.getCategoryId());
        assertEquals(productCode, DTOentity.getProductCode());
    }

    @Test
    void instantiateEmptyEntity(){
        int id = 0;
        String name = null;
        float price = 0;
        int categoryId = 0;
        String productCode = null;

        ProductForAlterationDTO DTOentity = new ProductForAlterationDTO();

        assertEquals(id, DTOentity.getId());
        assertEquals(name, DTOentity.getName());
        assertEquals(price, DTOentity.getPrice());
        assertEquals(categoryId, DTOentity.getCategoryId());
        assertEquals(productCode, DTOentity.getProductCode());
    }

    @Test
    void entityValidationTestForUpdateSuccess(){
        int id = 5;
        String name = "toothpast";
        double price = 13.6;
        int categoryId = 5;
        String productCode = "hlth002";

        ProductForAlterationDTO DTOentity = new ProductForAlterationDTO(
            id,
            name,
            price,
            categoryId,
            productCode
        );

        assertFalse(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailure(){
        int id = 5;
        String name = "toothpast";
        double price = 0.0;
        int categoryId = 0;
        String productCode = "hlth002";

        ProductForAlterationDTO DTOentity = new ProductForAlterationDTO(
                id,
                name,
                price,
                categoryId,
                productCode
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForCreationSuccess(){
        int id = 0;
        String name = "toothpast";
        double price = 13.6;
        int categoryId = 5;
        String productCode = "hlth002";

        ProductForAlterationDTO DTOentity = new ProductForAlterationDTO(
            id,
            name,
            price,
            categoryId,
            productCode
        );

        assertFalse(DTOentity.validateForCreation());
    }

    @Test
    void entityValidationTestForCreationFailure(){
        int id = 0;
        String name = "toothpast";
        double price = 0.0;
        int categoryId = 0;
        String productCode = "hlth002";

        ProductForAlterationDTO DTOentity = new ProductForAlterationDTO(
                id,
                name,
                price,
                categoryId,
                productCode
        );

        assertTrue(DTOentity.validateForCreation());
    }
}
