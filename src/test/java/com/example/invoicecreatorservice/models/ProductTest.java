package com.example.invoicecreatorservice.models;

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

        Product product = new Product(
            id,
            name,
            price,
            categoryId,
            productCode
        );

        assertEquals(id, product.getId());
        assertEquals(name, product.getName());
        double getPrice = product.getPrice();
        assertEquals(price, product.getPrice());
        assertEquals(categoryId, product.getCategoryId());
        assertEquals(productCode, product.getProductCode());
    }

    @Test
    void instantiateEmptyEntity(){
        int id = 0;
        String name = null;
        float price = 0;
        int categoryId = 0;
        String productCode = null;

        Product product = new Product();

        assertEquals(id, product.getId());
        assertEquals(name, product.getName());
        assertEquals(price, product.getPrice());
        assertEquals(categoryId, product.getCategoryId());
        assertEquals(productCode, product.getProductCode());
    }
}
