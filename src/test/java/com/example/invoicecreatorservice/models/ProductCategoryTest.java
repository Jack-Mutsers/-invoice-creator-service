package com.example.invoicecreatorservice.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductCategoryTest {
    @Test
    public void instantiateEntity(){
        int id = 5;
        String name = "healthcare";
        int btw = 23;

        ProductCategory productCategory = new ProductCategory(
            id,
            name,
            btw
        );

        assertEquals(id, productCategory.getId());
        assertEquals(name, productCategory.getName());
        assertEquals(btw, productCategory.getBtw());
    }


    @Test
    public void instantiateEmptyEntity(){
        int id = 0;
        String name = null;
        int btw = 0;

        ProductCategory productCategory = new ProductCategory();

        assertEquals(id, productCategory.getId());
        assertEquals(name, productCategory.getName());
        assertEquals(btw, productCategory.getBtw());
    }
}
