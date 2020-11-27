package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.objects.data_transfer_objects.CompanyDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.CompanyForAlterationDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.CustomerDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.CustomerForAlterationDTO;
import com.example.invoicecreatorservice.objects.models.Company;
import com.example.invoicecreatorservice.objects.models.Customer;
import com.example.invoicecreatorservice.repositories.CustomerRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerServiceTest {

    @Mock
    CustomerRepo repo;

    @InjectMocks
    CustomerService service;

    private final List<Customer> entityList = new ArrayList<>();

    private CustomerServiceTest(){
        entityList.add(new Customer(1, "Jhon Doe", "kerkstraat", "5926 DF", "Asten", 1));
        entityList.add(new Customer(2, "Arnold Schwarzenegger", "titanusstraat", "6943 RC", "Geldrop", 0));
        entityList.add(new Customer(3, "Tommy Blinder", "zonnenbloemlaan", "3496 PL", "heeze", 0));
        entityList.add(new Customer(4, "jhon snow", "st.martinlaan", "8512 BM", "Eindhoven", 3));
        entityList.add(new Customer(5, "henk jansen", "testlane 64", "1234 AB", "Testvile", 5));
    }

    @Test
    void getCustomerTest(){
        //Arrange
        Customer entity = entityList.get(1);

        //Prepare overwrites
        when(repo.findById(entity.getId())).thenReturn(entity);

        //Act
        CustomerDTO resultEntity = service.getCustomer(entity.getId());
        CustomerDTO expectedEntity = new CustomerDTO(entity);

        //Assert
        assertEquals(expectedEntity.getId(), resultEntity.getId());
        assertEquals(expectedEntity.getName(), resultEntity.getName());
        assertEquals(expectedEntity.getAddress(), resultEntity.getAddress());
        assertEquals(expectedEntity.getZipcode(), resultEntity.getZipcode());
        assertEquals(expectedEntity.getCity(), resultEntity.getCity());
    }

    @Test
    void getAllCustomersTest(){
        //Prepare
        when(repo.findAll()).thenReturn(entityList);

        //Act
        List<Customer> resultEntity = (List) service.getAllCustomers();

        //Assert
        assertEquals(entityList.size(), resultEntity.size());
    }

    @Test
    void deleteCustomerTestSuccess(){
        //Arrange
        Customer entity = entityList.get(2);

        //Prepare overwrites
        when(repo.findById(entity.getId())).thenReturn(entity);

        //Act
        boolean result = service.deleteCustomer(entity.getId());

        //Assert
        assertTrue(result);
    }

    @Test
    void deleteCustomerTestExceptionFailure(){
        //Arrange
        Customer entity = entityList.get(2);

        //Prepare overwrites
        when(repo.findById(entity.getId())).thenReturn(entity);
        doThrow(new EmptyResultDataAccessException(1000)).when(repo).deleteById(notNull());

        //Act
        Boolean result = service.deleteCustomer(entity.getId());

        //Assert
        assertFalse(result);
    }

    @Test
    void createCustomerTestSuccess(){
        //Arrange
        Customer entity = entityList.get(3);
        CustomerForAlterationDTO entityDTO = new CustomerForAlterationDTO(
                0,
                entity.getName(),
                entity.getAddress(),
                entity.getZipcode(),
                entity.getCity(),
                entity.getUserId()
        );

        //Prepare overwrites
        when(repo.save((Customer) notNull())).thenReturn(entity);

        //Act
        CustomerDTO resultEntity = service.createCustomer(entityDTO);
        CustomerDTO expectedEntity = new CustomerDTO(entity);

        //Assert
        assertEquals(expectedEntity.getId(), resultEntity.getId());
        assertEquals(expectedEntity.getName(), resultEntity.getName());
        assertEquals(expectedEntity.getAddress(), resultEntity.getAddress());
        assertEquals(expectedEntity.getZipcode(), resultEntity.getZipcode());
        assertEquals(expectedEntity.getCity(), resultEntity.getCity());
    }

    @Test
    void createCustomerTestExceptionFailure(){
        //Arrange

        //Prepare overwrites
        when(repo.save((Customer) isNull())).thenThrow(new IllegalArgumentException("Target object must not be null"));

        //Act
        CustomerDTO resultEntity = service.createCustomer(null);

        //Assert
        assertNull(resultEntity);
    }

    @Test
    void UpdateCustomerTestSuccess(){
        //Arrange
        Customer entity = entityList.get(3);
        entity.setName("Karel");
        CustomerForAlterationDTO entityDTO = new CustomerForAlterationDTO(
                4,
                entity.getName(),
                entity.getAddress(),
                entity.getZipcode(),
                entity.getCity(),
                entity.getUserId()
        );

        //Prepare overwrites
        when(repo.save((Customer) notNull())).thenReturn(entity);

        //Act
        boolean result = service.updateCustomer(entityDTO);

        //Assert
        assertTrue(result);
    }

    @Test
    void UpdateCustomerTestFailure(){
        //Arrange
        Customer entity = entityList.get(3);
        CustomerForAlterationDTO entityDTO = new CustomerForAlterationDTO(
                0,
                entity.getName(),
                entity.getAddress(),
                "",
                entity.getCity(),
                entity.getUserId()
        );

        //Prepare overwrites
        when(repo.save((Customer) notNull())).thenThrow(new NullPointerException("zipcode cannot be empty"));

        //Act
        boolean result = service.updateCustomer(entityDTO);

        //Assert
        assertFalse(result);
    }
}
