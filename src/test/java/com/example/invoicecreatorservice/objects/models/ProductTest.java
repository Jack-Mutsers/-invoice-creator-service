package com.example.invoicecreatorservice.objects.models;

import com.example.invoicecreatorservice.objects.data_transfer_objects.ProductForAlterationDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductTest {

    @Test
    void instantiateEntity(){
        int id = 5;
        String name = "toothpast";
        double price = 13.6;
        int categoryId = 5;
        String productCode = "hlth002";

        Product entity = new Product(
            id,
            name,
            price,
            categoryId,
            productCode
        );

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(price, entity.getPrice());
        assertEquals(categoryId, entity.getCategoryId());
        assertEquals(productCode, entity.getProductCode());
    }

    @Test
    void instantiateEmptyEntity(){
        int id = 0;
        String name = null;
        float price = 0;
        int categoryId = 0;
        String productCode = null;

        Product entity = new Product();

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(price, entity.getPrice());
        assertEquals(categoryId, entity.getCategoryId());
        assertEquals(productCode, entity.getProductCode());
    }

    @Test
    void instantiateEntityByDTO(){
        int id = 5;
        String name = "toothpast";
        double price = 13.6;
        int categoryId = 5;
        String productCode = "hlth002";

        ProductForAlterationDTO entityDTO = new ProductForAlterationDTO(
                id,
                name,
                price,
                categoryId,
                productCode
        );

        Product entity = new Product(entityDTO);

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(price, entity.getPrice());
        assertEquals(categoryId, entity.getCategoryId());
        assertEquals(productCode, entity.getProductCode());
    }

    @Test
    void fillEmptyEntity(){
        int id = 5;
        String name = "toothpast";
        double price = 13.6;
        int categoryId = 5;
        String productCode = "hlth002";

        Product entity = new Product();

        entity.setId(id);
        entity.setName(name);
        entity.setPrice(price);
        entity.setCategoryId(categoryId);
        entity.setProductCode(productCode);

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(price, entity.getPrice());
        assertEquals(categoryId, entity.getCategoryId());
        assertEquals(productCode, entity.getProductCode());
    }
}
