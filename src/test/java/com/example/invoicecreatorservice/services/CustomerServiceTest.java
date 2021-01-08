package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.objects.data_transfer_objects.CustomerDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.CustomerForAlterationDTO;
import com.example.invoicecreatorservice.objects.models.Customer;
import com.example.invoicecreatorservice.repositories.CustomerRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;

import static com.example.invoicecreatorservice.helpers.tools.Helper.emptyIfNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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
        entityList.add(new Customer(1, "Jhon Doe", "kerkstraat", "5926 DF", "Asten", 1, 1, "sd3Fv354Sf"));
        entityList.add(new Customer(2, "Arnold Schwarzenegger", "titanusstraat", "6943 RC", "Geldrop", 0, 1, "sd3Fv354Sf"));
        entityList.add(new Customer(3, "Tommy Blinder", "zonnenbloemlaan", "3496 PL", "heeze", 0, 2, "1Dfr23AS2d"));
        entityList.add(new Customer(4, "jhon snow", "st.martinlaan", "8512 BM", "Eindhoven", 3, 2, "oPsdf34Fs3"));
        entityList.add(new Customer(5, "henk jansen", "testlane 64", "1234 AB", "Testvile", 5, 1, "sd3Fv354Sf"));
    }

    private List<Customer> findByCompany(int companyId){
        List<Customer> result = new ArrayList<>();
        for(Customer customer : emptyIfNull(entityList)){
            if(customer.getCompanyId() == companyId){
                result.add(customer);
            }
        }

        return result;
    }

    @Test
    void findByCompanyTest(){
        //Prepare
        int companyId = 1;
        List<Customer> expected = new ArrayList<>();
        expected.add(entityList.get(0));
        expected.add(entityList.get(1));
        expected.add(entityList.get(4));

        //Act
        List<Customer> resultEntity = this.findByCompany(companyId);

        //Assert
        assertEquals(expected.get(0), resultEntity.get(0));
        assertEquals(expected.get(1), resultEntity.get(1));
        assertEquals(expected.get(2), resultEntity.get(2));
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
        int companyId = 1;

        when(repo.findAllByCompanyId(companyId)).thenReturn(entityList);

        //Act
        List<Customer> resultEntity = (List) service.getAllCustomers(companyId);

        //Assert
        assertEquals(entityList.size(), resultEntity.size());
    }

    @Test
    void getMyCustomersIds(){
        //Prepare
        List<Customer> customerList = findByCompany(1);
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(5);
        String contactCode = entityList.get(0).getContactCode();

        when(repo.findAllByContactCode(contactCode)).thenReturn(customerList);

        //Act
        List<Customer> resultEntity = (List) service.getMyCustomerIds(contactCode);

        //Assert
        assertEquals(expected.size(), resultEntity.size());
    }

    @Test
    void deleteCustomerTestSuccess(){
        //Arrange
        Customer entity = entityList.get(2);

        //Prepare overwrites
        when(repo.findById(entity.getId())).thenReturn(entity);

        //Act
        boolean result = service.deleteCustomer(entity.getId(), entity.getCompanyId());

        //Assert
        assertTrue(result);
    }

    @Test
    void deleteCustomerIncorrectCompanyTest(){
        //Arrange
        int companyId = 6;
        Customer entity = entityList.get(2);

        //Prepare overwrites
        when(repo.findById(entity.getId())).thenReturn(entity);

        //Act
        boolean result = service.deleteCustomer(entity.getId(), companyId);

        //Assert
        assertFalse(result);
    }

    @Test
    void deleteCustomerTestExceptionFailure(){
        //Arrange
        Customer entity = entityList.get(2);

        //Prepare overwrites
        when(repo.findById(entity.getId())).thenReturn(entity);
        doThrow(new EmptyResultDataAccessException(1000)).when(repo).deleteById(notNull());

        //Act
        Boolean result = service.deleteCustomer(entity.getId(), entity.getCompanyId());

        //Assert
        assertFalse(result);
    }

    @Test
    void deleteAllCompanyCustomersTest(){
        //Arrange
        int companyId = 1;

        //Act
        boolean result = service.deleteAllCompanyCustomers(companyId);

        //Assert
        assertTrue(result);
    }

    @Test
    void deleteAllCompanyCustomersTestExceptionFailure(){
        //Arrange
        int companyId = 0;

        //Prepare overwrites
        doThrow(new EmptyResultDataAccessException(1000)).when(repo).deleteAllByCompanyId(companyId);

        //Act
        boolean result = service.deleteAllCompanyCustomers(companyId);

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
                entity.getUserId(),
                entity.getCompanyId()
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
                entity.getUserId(),
                entity.getCompanyId()
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
                entity.getUserId(),
                entity.getCompanyId()
        );

        //Prepare overwrites
        when(repo.save((Customer) notNull())).thenThrow(new NullPointerException("zipcode cannot be empty"));

        //Act
        boolean result = service.updateCustomer(entityDTO);

        //Assert
        assertFalse(result);
    }
}
