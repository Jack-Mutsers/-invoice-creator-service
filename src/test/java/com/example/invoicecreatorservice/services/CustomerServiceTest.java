package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.data_transfer_objects.CustomerDTO;
import com.example.invoicecreatorservice.models.Customer;
import com.example.invoicecreatorservice.repositories.CompanyRepo;
import com.example.invoicecreatorservice.repositories.CustomerRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceTest {

    @Mock
    CustomerRepo repo;

    @InjectMocks
    CustomerService service;

    private final List<Customer> entityList = new ArrayList<>();

    private CustomerServiceTest(){
        entityList.add(new Customer(1, "Jhon Doe", "kerkstraat", "5926 DF", "Asten"));
        entityList.add(new Customer(2, "Arnold Schwarzenegger", "titanusstraat", "6943 RC", "Geldrop"));
        entityList.add(new Customer(3, "Tommy Blinder", "zonnenbloemlaan", "3496 PL", "heeze"));
        entityList.add(new Customer(4, "jhon snow", "st.martinlaan", "8512 BM", "Eindhoven"));
        entityList.add(new Customer(5, "henk jansen", "testlane 64", "1234 AB", "Testvile"));
    }

    @Test
    void getCustomerTest(){
        //Arrange
        Customer entity = entityList.get(1);

        //Prepare
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
}
