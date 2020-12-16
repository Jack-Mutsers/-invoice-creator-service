package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.Company;
import com.example.invoicecreatorservice.objects.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductForAlterationDTOTest {

    private final Product entity = new Product(5, "toothpast", 13.6, 5, "hlth002",1);

    @Test
    void instantiateEntity(){
        int priceResult = 1360;

        ProductForAlterationDTO DTOentity = new ProductForAlterationDTO(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getCategoryId(),
                entity.getProductCode(),
                entity.getCompanyId()
        );

        assertEquals(entity.getId(), DTOentity.getId());
        assertEquals(entity.getName(), DTOentity.getName());
        assertEquals(priceResult, DTOentity.getPrice());
        assertEquals(entity.getCategoryId(), DTOentity.getCategoryId());
        assertEquals(entity.getProductCode(), DTOentity.getProductCode());
        assertEquals(entity.getCompanyId(), DTOentity.getCompanyId());
    }

    @Test
    void instantiateEmptyEntity(){
        int id = 0;
        String name = null;
        float price = 0;
        int categoryId = 0;
        String productCode = null;
        int companyId = 0;

        ProductForAlterationDTO DTOentity = new ProductForAlterationDTO();

        assertEquals(id, DTOentity.getId());
        assertEquals(name, DTOentity.getName());
        assertEquals(price, DTOentity.getPrice());
        assertEquals(categoryId, DTOentity.getCategoryId());
        assertEquals(productCode, DTOentity.getProductCode());
        assertEquals(companyId, DTOentity.getCompanyId());
    }

    @Test
    void setVariablesTest(){
        int id = 2;
        double price = 13.6;
        int priceResult = 1360;
        int companyId = 0;

        ProductForAlterationDTO DTOentity = new ProductForAlterationDTO();
        DTOentity.setId(id);
        DTOentity.setPrice(price);
        DTOentity.setCompanyId(companyId);

        assertEquals(id, DTOentity.getId());
        assertEquals(priceResult, DTOentity.getPrice());
        assertEquals(companyId, DTOentity.getCompanyId());
    }

    @Test
    void entityValidationTestForUpdateSuccess(){

        ProductForAlterationDTO DTOentity = new ProductForAlterationDTO(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getCategoryId(),
                entity.getProductCode(),
                entity.getCompanyId()
        );

        assertFalse(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureOne(){

        ProductForAlterationDTO DTOentity = new ProductForAlterationDTO(
                0,
                entity.getName(),
                entity.getPrice(),
                entity.getCategoryId(),
                entity.getProductCode(),
                entity.getCompanyId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureTwo(){

        ProductForAlterationDTO DTOentity = new ProductForAlterationDTO(
                entity.getId(),
                null,
                entity.getPrice(),
                entity.getCategoryId(),
                entity.getProductCode(),
                entity.getCompanyId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureThree(){

        ProductForAlterationDTO DTOentity = new ProductForAlterationDTO(
                entity.getId(),
                entity.getName(),
                0,
                entity.getCategoryId(),
                entity.getProductCode(),
                entity.getCompanyId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureFour(){

        ProductForAlterationDTO DTOentity = new ProductForAlterationDTO(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                0,
                entity.getProductCode(),
                entity.getCompanyId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureFive(){

        ProductForAlterationDTO DTOentity = new ProductForAlterationDTO(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getCategoryId(),
                "  ",
                entity.getCompanyId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureSix(){

        ProductForAlterationDTO DTOentity = new ProductForAlterationDTO(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getCategoryId(),
                entity.getProductCode(),
                0
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
        int companyId = 1;

        ProductForAlterationDTO DTOentity = new ProductForAlterationDTO(
            id,
            name,
            price,
            categoryId,
            productCode,
            companyId
        );

        assertFalse(DTOentity.validateForCreation());
    }

}
