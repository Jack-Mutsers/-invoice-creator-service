package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductCategoryDTOTest {
    @Test
    void instantiateEntity(){
        int id = 5;
        String name = "healthcare";
        int btw = 23;

        ProductCategory entity = new ProductCategory(
                id,
                name,
                btw
        );

        ProductCategoryDTO DTOentity = new ProductCategoryDTO(entity);

        assertEquals(id, DTOentity.getId());
        assertEquals(name, DTOentity.getName());
        assertEquals(btw, DTOentity.getBtw());
    }

    @Test
    void instantiateEmptyEntity(){
        int id = 0;
        String name = null;
        int btw = 0;

        ProductCategory DTOentity = new ProductCategory();

        assertEquals(id, DTOentity.getId());
        assertEquals(name, DTOentity.getName());
        assertEquals(btw, DTOentity.getBtw());
    }

    @Test
    void fillEmptyEntity(){
        int id = 5;
        String name = "healthcare";
        int btw = 23;

        ProductCategory DTOentity = new ProductCategory();

        DTOentity.setId(id);
        DTOentity.setName(name);
        DTOentity.setBtw(btw);

        assertEquals(id, DTOentity.getId());
        assertEquals(name, DTOentity.getName());
        assertEquals(btw, DTOentity.getBtw());
    }
}
