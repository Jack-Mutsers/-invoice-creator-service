package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.objects.data_transfer_objects.ProductCategoryDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.ProductCategoryForAlterationDTO;
import com.example.invoicecreatorservice.objects.models.*;
import com.example.invoicecreatorservice.repositories.ProductCategoryRepo;
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
class ProductCategoryServiceTest {
    @Mock
    ProductCategoryRepo repo;

    @InjectMocks
    ProductCategoryService service;

    private final List<ProductCategory> entityList = new ArrayList<>();

    private ProductCategoryServiceTest(){
        entityList.add(new ProductCategory(1,"Food",6,1 ));
        entityList.add(new ProductCategory(2,"Hardware",21,2 ));
        entityList.add(new ProductCategory(3,"BeautyProducts",21,1 ));
        entityList.add(new ProductCategory(4,"Toys",21,3 ));
        entityList.add(new ProductCategory(5,"Healthcare",21,1 ));
    }

    @Test
    void getCategoryTest(){
        //Arrange
        int id = 1;
        ProductCategory category = entityList.get(0);

        //Prepare overwrites
        when(repo.findById(id)).thenReturn(category);

        //Act
        ProductCategoryDTO result = service.getCategory(id);

        //Assert
        assertEquals(category.getId(), result.getId());
        assertEquals(category.getName(), result.getName());
        assertEquals(category.getBtw(), result.getBtw());
    }

    @Test
    void getCategoryNullTest(){
        //Arrange
        int id = 0;

        //Prepare overwrites
        when(repo.findById(id)).thenReturn(null);

        //Act
        ProductCategoryDTO result = service.getCategory(id);

        //Assert
        assertEquals(0, result.getId());
        assertNull(result.getName());
        assertEquals(0, result.getBtw());
    }

    @Test
    void getAllCategoryTest(){
        //Arrange
        int companyId = 1;
        List<ProductCategory> categoryList = new ArrayList<>();
        categoryList.add(entityList.get(0));
        categoryList.add(entityList.get(2));
        categoryList.add(entityList.get(4));


        //Prepare overwrites
        when(repo.findAllByCompanyId(companyId)).thenReturn(categoryList);

        //Act
        List<ProductCategory> result = Lists.newArrayList(service.getAllCategory(companyId));

        //Assert
        assertEquals(3, result.size());
        assertEquals(categoryList.get(0).getId(), result.get(0).getId());
        assertEquals(categoryList.get(1).getId(), result.get(1).getId());
        assertEquals(categoryList.get(2).getId(), result.get(2).getId());

    }

    @Test
    void getAllCategoryEmptyTest(){
        //Arrange
        int companyId = 0;

        //Prepare overwrites
        when(repo.findAllByCompanyId(companyId)).thenReturn(new ArrayList<>());

        //Act
        List<ProductCategory> result = Lists.newArrayList(service.getAllCategory(companyId));

        //Assert
        assertEquals(0, result.size());
    }

    @Test
    void deleteCategoryTest(){
        //Arrange
        ProductCategory category = entityList.get(0);
        int id = category.getId();
        int companyId = category.getCompanyId();

        //Prepare overwrites
        when(repo.findById(id)).thenReturn(category);

        //Act
        boolean result = service.deleteCategory(id, companyId);

        //Assert
        assertTrue(result);
    }

    @Test
    void deleteCategoryNullTest(){
        //Arrange
        ProductCategory category = entityList.get(0);
        int id = 0;
        int companyId = category.getCompanyId();

        //Prepare overwrites
        when(repo.findById(id)).thenReturn(null);

        //Act
        boolean result = service.deleteCategory(id, companyId);

        //Assert
        assertFalse(result);
    }

    @Test
    void deleteCategoryInvalidCompanyTest(){
        //Arrange
        ProductCategory category = entityList.get(0);
        int id = category.getId();
        int companyId = 0;

        //Prepare overwrites
        when(repo.findById(id)).thenReturn(category);

        //Act
        boolean result = service.deleteCategory(id, companyId);

        //Assert
        assertFalse(result);
    }

    @Test
    void deleteCategoryExceptionTest(){
        //Arrange
        ProductCategory category = entityList.get(0);
        int id = category.getId();
        int companyId = category.getCompanyId();

        //Prepare overwrites
        doThrow(new EmptyResultDataAccessException(20)).when(repo).findById(id);

        //Act
        boolean result = service.deleteCategory(id, companyId);

        //Assert
        assertFalse(result);
    }

    @Test
    void deleteAllCompanyCategoriesTest(){
        //Arrange
        int companyId = 1;

        //Prepare overwrites

        //Act
        boolean result = service.deleteAllCompanyCategories(companyId);

        //Assert
        assertTrue(result);
    }

    @Test
    void deleteAllCompanyCategoriesExceptionTest(){
        //Arrange
        ProductCategory category = entityList.get(0);
        int companyId = category.getCompanyId();

        //Prepare overwrites
        doThrow(new EmptyResultDataAccessException(20)).when(repo).deleteAllByCompanyId(companyId);

        //Act
        boolean result = service.deleteAllCompanyCategories(companyId);

        //Assert
        assertFalse(result);
    }

    @Test
    void createCategoryTest(){
        //Arrange
        ProductCategory category = entityList.get(2);
        ProductCategoryForAlterationDTO alterationDTO = new ProductCategoryForAlterationDTO(
          0,
          category.getName(),
          category.getBtw(),
          category.getCompanyId()
        );

        //Prepare overwrites
        when(repo.save((ProductCategory)notNull())).thenReturn(category);

        //Act
        ProductCategoryDTO result = service.createCategory(alterationDTO);

        //Assert
        assertEquals(category.getId(), result.getId());
        assertEquals(category.getName(), result.getName());
        assertEquals(category.getBtw(), result.getBtw());
    }

    @Test
    void createCategoryExceptionTest(){
        //Arrange
        ProductCategory category = entityList.get(2);
        ProductCategoryForAlterationDTO alterationDTO = new ProductCategoryForAlterationDTO(
                0,
                category.getName(),
                category.getBtw(),
                category.getCompanyId()
        );

        //Prepare overwrites
        doThrow(new EmptyResultDataAccessException(20)).when(repo).save((ProductCategory)notNull());

        //Act
        ProductCategoryDTO result = service.createCategory(alterationDTO);

        //Assert
        assertNull(result);
    }

    @Test
    void updateCategoryTest(){
        //Arrange
        ProductCategory category = entityList.get(2);
        ProductCategoryForAlterationDTO alterationDTO = new ProductCategoryForAlterationDTO(
                3,
                category.getName(),
                category.getBtw(),
                category.getCompanyId()
        );

        //Prepare overwrites
        when(repo.save((ProductCategory)notNull())).thenReturn(category);

        //Act
        boolean result = service.updateCategory(alterationDTO);

        //Assert
        assertTrue(result);
    }

    @Test
    void updateCategoryExceptionTest(){
        //Arrange
        ProductCategory category = entityList.get(2);
        ProductCategoryForAlterationDTO alterationDTO = new ProductCategoryForAlterationDTO(
                0,
                category.getName(),
                category.getBtw(),
                category.getCompanyId()
        );

        //Prepare overwrites
        doThrow(new EmptyResultDataAccessException(20)).when(repo).save((ProductCategory)notNull());

        //Act
        boolean result = service.updateCategory(alterationDTO);

        //Assert
        assertFalse(result);
    }

}
