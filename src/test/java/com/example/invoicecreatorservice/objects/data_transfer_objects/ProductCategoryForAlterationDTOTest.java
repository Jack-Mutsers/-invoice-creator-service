package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductCategoryForAlterationDTOTest {

    private final ProductCategory entity = new ProductCategory(2, "healthcare", 23, 1);

    @Test
    void instantiateEntity(){

        ProductCategoryForAlterationDTO DTOentity = new ProductCategoryForAlterationDTO(
                entity.getId(),
                entity.getName(),
                entity.getBtw(),
                entity.getCompanyId()
        );

        assertEquals(entity.getId(), DTOentity.getId());
        assertEquals(entity.getName(), DTOentity.getName());
        assertEquals(entity.getBtw(), DTOentity.getBtw());
        assertEquals(entity.getCompanyId(), DTOentity.getCompanyId());
    }

    @Test
    void instantiateEmptyEntity(){
        int id = 0;
        String name = null;
        int btw = 0;
        int companyId = 0;

        ProductCategoryForAlterationDTO DTOentity = new ProductCategoryForAlterationDTO();

        assertEquals(id, DTOentity.getId());
        assertEquals(name, DTOentity.getName());
        assertEquals(btw, DTOentity.getBtw());
        assertEquals(companyId, DTOentity.getCompanyId());
    }

    @Test
    void setVariablesTest(){
        int id = 2;
        int companyId = 1;

        ProductCategoryForAlterationDTO DTOentity = new ProductCategoryForAlterationDTO();
        DTOentity.setId(id);
        DTOentity.setCompanyId(companyId);

        assertEquals(id, DTOentity.getId());
        assertEquals(companyId, DTOentity.getCompanyId());
    }

    @Test
    void entityValidationTestForUpdateSuccess(){

        ProductCategoryForAlterationDTO DTOentity = new ProductCategoryForAlterationDTO(
                entity.getId(),
                entity.getName(),
                entity.getBtw(),
                entity.getCompanyId()
        );

        assertFalse(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureOne(){

        ProductCategoryForAlterationDTO DTOentity = new ProductCategoryForAlterationDTO(
                0,
                entity.getName(),
                entity.getBtw(),
                entity.getCompanyId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureTwo(){

        ProductCategoryForAlterationDTO DTOentity = new ProductCategoryForAlterationDTO(
                entity.getId(),
                null,
                entity.getBtw(),
                entity.getCompanyId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureTree(){

        ProductCategoryForAlterationDTO DTOentity = new ProductCategoryForAlterationDTO(
                entity.getId(),
                "",
                entity.getBtw(),
                entity.getCompanyId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureFour(){

        ProductCategoryForAlterationDTO DTOentity = new ProductCategoryForAlterationDTO(
                entity.getId(),
                entity.getName(),
                0,
                entity.getCompanyId()
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForUpdateFailureFive(){

        ProductCategoryForAlterationDTO DTOentity = new ProductCategoryForAlterationDTO(
                entity.getId(),
                entity.getName(),
                entity.getBtw(),
                0
        );

        assertTrue(DTOentity.validateForUpdate());
    }

    @Test
    void entityValidationTestForCreationSuccess(){
        int id = 0;
        String name = "healthcare";
        int btw = 23;
        int companyId = 1;

        ProductCategoryForAlterationDTO DTOentity = new ProductCategoryForAlterationDTO(
                id,
                name,
                btw,
                companyId
        );

        assertFalse(DTOentity.validateForCreation());
    }

}
