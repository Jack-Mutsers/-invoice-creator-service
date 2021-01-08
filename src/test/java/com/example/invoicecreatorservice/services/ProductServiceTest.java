package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.objects.data_transfer_objects.ProductCategoryDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.ProductDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.ProductForAlterationDTO;
import com.example.invoicecreatorservice.objects.models.Product;
import com.example.invoicecreatorservice.repositories.ProductRepo;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceTest {
    @Mock
    ProductRepo repo;

    @Mock
    ProductCategoryService categoryService;

    @InjectMocks
    ProductService service;

    private final List<Product> entityList = new ArrayList<>();

    private ProductServiceTest(){
        entityList.add(new Product(1,"Pudding",82,1,"FOOD_001",1));
        entityList.add(new Product(2,"Shampoo",1960,3,"BTPR_001",4));
        entityList.add(new Product(3,"CatFood",1250,4,"anml_001",8));
        entityList.add(new Product(4,"Computer",49999,6,"TOYS_001",2));
        entityList.add(new Product(5,"Cheese",531,1,"FOOD_002",1));
    }

    @Test
    void getCategoryTest(){
        //Arrange
        int id = 1;
        Product product = entityList.get(0);

        //Prepare overwrites
        when(repo.findById(id)).thenReturn(product);
        when(categoryService.getCategory(product.getCategoryId())).thenReturn(new ProductCategoryDTO());

        //Act
        ProductDTO result = service.getProduct(id);

        //Assert
        assertEquals(product.getId(), result.getId());
        assertEquals(product.getName(), result.getName());
        assertEquals(product.getPrice(), result.getPrice());
        assertEquals(product.getProductCode(), result.getProductCode());
        assertEquals(product.getCategoryId(), result.getCategoryId());
    }

    @Test
    void getCategoryNullTest(){
        //Arrange
        int id = 0;

        //Prepare overwrites
        when(repo.findById(id)).thenReturn(null);

        //Act
        ProductDTO result = service.getProduct(id);

        //Assert
        assertEquals(0, result.getId());
        assertNull(result.getName());
        assertEquals(0, result.getPrice());
        assertNull(result.getProductCode());
        assertEquals(0, result.getCategoryId());
    }

    @Test
    void getAllCategoryTest(){
        //Arrange
        int companyId = 1;
        List<Product> productList = new ArrayList<>();
        productList.add(entityList.get(0));
        productList.add(entityList.get(4));

        //Prepare overwrites
        when(repo.findAllByCompanyId(companyId)).thenReturn(productList);
        when(categoryService.getCategory(productList.get(0).getCategoryId())).thenReturn(new ProductCategoryDTO());
        when(categoryService.getCategory(productList.get(1).getCategoryId())).thenReturn(new ProductCategoryDTO());

        //Act
        List<ProductDTO> result = Lists.newArrayList(service.getAllProducts(companyId));

        //Assert
        assertEquals(2, result.size());
        assertEquals(productList.get(0).getId(), result.get(0).getId());
        assertEquals(productList.get(1).getId(), result.get(1).getId());

    }

    @Test
    void getAllCategoryEmptyTest(){
        //Arrange
        int companyId = 0;

        //Prepare overwrites
        when(repo.findAllByCompanyId(companyId)).thenReturn(new ArrayList<>());

        //Act
        List<ProductDTO> result = Lists.newArrayList(service.getAllProducts(companyId));

        //Assert
        assertEquals(0, result.size());
    }

    @Test
    void deleteCategoryTest(){
        //Arrange
        Product product= entityList.get(0);
        int id = product.getId();
        int companyId = product.getCompanyId();

        //Prepare overwrites
        when(repo.findById(id)).thenReturn(product);

        //Act
        boolean result = service.deleteProduct(id, companyId);

        //Assert
        assertTrue(result);
    }

    @Test
    void deleteCategoryNullTest(){
        //Arrange
        Product product = entityList.get(0);
        int id = 0;
        int companyId = product.getCompanyId();

        //Prepare overwrites
        when(repo.findById(id)).thenReturn(null);

        //Act
        boolean result = service.deleteProduct(id, companyId);

        //Assert
        assertFalse(result);
    }

    @Test
    void deleteCategoryInvalidCompanyTest(){
        //Arrange
        Product product = entityList.get(0);
        int id = product.getId();
        int companyId = 0;

        //Prepare overwrites
        when(repo.findById(id)).thenReturn(product);

        //Act
        boolean result = service.deleteProduct(id, companyId);

        //Assert
        assertFalse(result);
    }

    @Test
    void deleteCategoryExceptionTest(){
        //Arrange
        Product product = entityList.get(0);
        int id = product.getId();
        int companyId = product.getCompanyId();

        //Prepare overwrites
        doThrow(new EmptyResultDataAccessException(20)).when(repo).findById(id);

        //Act
        boolean result = service.deleteProduct(id, companyId);

        //Assert
        assertFalse(result);
    }

    @Test
    void deleteAllCompanyCategoriesTest(){
        //Arrange
        int companyId = 1;

        //Prepare overwrites

        //Act
        boolean result = service.deleteAllCompanyProducts(companyId);

        //Assert
        assertTrue(result);
    }

    @Test
    void deleteAllCompanyCategoriesExceptionTest(){
        //Arrange
        Product product = entityList.get(0);
        int companyId = product.getCompanyId();

        //Prepare overwrites
        doThrow(new EmptyResultDataAccessException(20)).when(repo).deleteAllByCompanyId(companyId);

        //Act
        boolean result = service.deleteAllCompanyProducts(companyId);

        //Assert
        assertFalse(result);
    }

    @Test
    void createCategoryTest(){
        //Arrange
        Product product = entityList.get(2);
        double price = product.getPrice() / 100.0;

        ProductForAlterationDTO alterationDTO = new ProductForAlterationDTO(
                0,
                product.getName(),
                price,
                product.getCategoryId(),
                product.getProductCode(),
                product.getCompanyId()
        );

        //Prepare overwrites
        when(repo.save((Product) notNull())).thenReturn(product);
        when(categoryService.getCategory(product.getCategoryId())).thenReturn(new ProductCategoryDTO());

        //Act
        ProductDTO result = service.createProduct(alterationDTO);

        //Assert
        assertEquals(product.getId(), result.getId());
        assertEquals(product.getName(), result.getName());
        assertEquals(product.getPrice(), result.getPrice());
        assertEquals(product.getProductCode(), result.getProductCode());
        assertEquals(product.getCategoryId(), result.getCategoryId());
    }

    @Test
    void createCategoryExceptionTest(){
        //Arrange
        Product product = entityList.get(2);
        double price = product.getPrice() / 100.0;
        ProductForAlterationDTO alterationDTO = new ProductForAlterationDTO(
                0,
                product.getName(),
                price,
                product.getCategoryId(),
                product.getProductCode(),
                product.getCompanyId()
        );

        //Prepare overwrites
        doThrow(new EmptyResultDataAccessException(20)).when(repo).save((Product)notNull());

        //Act
        ProductDTO result = service.createProduct(alterationDTO);

        //Assert
        assertNull(result);
    }

    @Test
    void updateCategoryTest(){
        //Arrange
        Product product = entityList.get(2);
        double price = product.getPrice() / 100.0;
        ProductForAlterationDTO alterationDTO = new ProductForAlterationDTO(
                3,
                product.getName(),
                price,
                product.getCategoryId(),
                product.getProductCode(),
                product.getCompanyId()
        );

        //Prepare overwrites
        when(repo.save((Product)notNull())).thenReturn(product);

        //Act
        boolean result = service.updateProduct(alterationDTO);

        //Assert
        assertTrue(result);
    }

    @Test
    void updateCategoryExceptionTest(){
        //Arrange
        Product product = entityList.get(2);
        double price = product.getPrice() / 100.0;
        ProductForAlterationDTO alterationDTO = new ProductForAlterationDTO(
                3,
                product.getName(),
                price,
                product.getCategoryId(),
                product.getProductCode(),
                product.getCompanyId()
        );

        //Prepare overwrites
        doThrow(new EmptyResultDataAccessException(20)).when(repo).save((Product) notNull());

        //Act
        boolean result = service.updateProduct(alterationDTO);

        //Assert
        assertFalse(result);
    }

}
