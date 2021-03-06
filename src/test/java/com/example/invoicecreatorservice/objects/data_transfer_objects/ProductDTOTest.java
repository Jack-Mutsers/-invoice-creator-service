package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductDTOTest {

    @Test
    void instantiateEntity(){
        int id = 5;
        String name = "toothpast";
        double price = 13.6;
        int categoryId = 5;
        String productCode = "hlth002";
        int companyId = 1;

        Product entity = new Product(
                id,
                name,
                price,
                categoryId,
                productCode,
                companyId
        );

        ProductDTO DTOentity = new ProductDTO(entity);

        assertEquals(id, DTOentity.getId());
        assertEquals(name, DTOentity.getName());
        assertEquals(price, DTOentity.getPrice());
        assertEquals(categoryId, DTOentity.getCategoryId());
        assertEquals(productCode, DTOentity.getProductCode());
    }

    @Test
    void instantiateEmptyEntity(){
        int id = 0;
        String name = null;
        double price = 0.0;
        int categoryId = 0;
        String productCode = null;

        ProductDTO DTOentity = new ProductDTO();

        assertEquals(id, DTOentity.getId());
        assertEquals(name, DTOentity.getName());
        assertEquals(price, DTOentity.getPrice());
        assertEquals(categoryId, DTOentity.getCategoryId());
        assertEquals(productCode, DTOentity.getProductCode());
    }

    @Test
    void fillEmptyEntity(){
        int id = 5;
        String name = "toothpast";
        double price = 13.6;
        int categoryId = 5;
        String productCode = "hlth002";

        ProductDTO DTOentity = new ProductDTO();

        DTOentity.setId(id);
        DTOentity.setName(name);
        DTOentity.setPrice(price);
        DTOentity.setCategoryId(categoryId);
        DTOentity.setCategory(new ProductCategoryDTO());
        DTOentity.setProductCode(productCode);

        assertEquals(id, DTOentity.getId());
        assertEquals(name, DTOentity.getName());
        assertEquals(price, DTOentity.getPrice());
        assertEquals(categoryId, DTOentity.getCategoryId());
        assertNotNull(DTOentity.getCategory());
        assertEquals(productCode, DTOentity.getProductCode());
    }

    @Test
    void getProductListTest(){
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1,"toothbrush",19.5,5,"hlth_001",2));
        productList.add(new Product(1,"toothpaste",7.5,5,"hlth_002",2));

        ProductDTO dto = new ProductDTO();

        List<ProductDTO> result = dto.getProductList(productList);

        assertEquals(productList.size(), result.size());
    }
}
