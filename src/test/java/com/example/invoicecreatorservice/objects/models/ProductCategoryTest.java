package com.example.invoicecreatorservice.objects.models;

import com.example.invoicecreatorservice.objects.data_transfer_objects.ProductCategoryForAlterationDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductCategoryTest {
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

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(btw, entity.getBtw());
    }

    @Test
    void instantiateEmptyEntity(){
        int id = 0;
        String name = null;
        int btw = 0;

        ProductCategory entity = new ProductCategory();

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(btw, entity.getBtw());
    }

    @Test
    void instantiateEntityByDTO(){
        int id = 5;
        String name = "healthcare";
        int btw = 23;

        ProductCategoryForAlterationDTO entityDTO = new ProductCategoryForAlterationDTO(
            id,
            name,
            btw
        );

        ProductCategory entity = new ProductCategory(entityDTO);

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(btw, entity.getBtw());
    }

    @Test
    void fillEmptyEntity(){
        int id = 5;
        String name = "healthcare";
        int btw = 23;

        ProductCategory entity = new ProductCategory();

        entity.setId(id);
        entity.setName(name);
        entity.setBtw(btw);

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(btw, entity.getBtw());
    }
}
