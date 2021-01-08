package com.example.invoicecreatorservice.objects.models;

import com.example.invoicecreatorservice.objects.data_transfer_objects.CustomerForAlterationDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CustomerTest {

    @Test
    void instantiateEntity(){
        int id = 5;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "Testvile";
        int userId = 0;
        int companyId = 1;

        Customer entity = new Customer(
            id,
            name,
            address,
            zipcode,
            city,
            userId,
            companyId
        );

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(address, entity.getAddress());
        assertEquals(zipcode, entity.getZipcode());
        assertEquals(city, entity.getCity());
    }

    @Test
    void instantiateEmptyEntity(){
        int id = 0;
        String name = null;
        String address = null;
        String zipcode = null;
        String city = null;

        Customer entity = new Customer();

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(address, entity.getAddress());
        assertEquals(zipcode, entity.getZipcode());
        assertEquals(city, entity.getCity());
    }

    @Test
    void instantiateEntityByDTO(){
        int id = 5;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "Testvile";
        int userId = 0;
        int companyId = 1;

        CustomerForAlterationDTO entityDTO = new CustomerForAlterationDTO(
            id,
            name,
            address,
            zipcode,
            city,
            userId,
            companyId
        );

        Customer entity = new Customer(entityDTO);

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(address, entity.getAddress());
        assertEquals(zipcode, entity.getZipcode());
        assertEquals(city, entity.getCity());
    }

    @Test
    void fillEmptyEntity(){
        int id = 5;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "Testvile";
        int userId = 51;
        int companyId = 3;

        Customer entity = new Customer();

        entity.setId(id);
        entity.setName(name);
        entity.setAddress(address);
        entity.setZipcode(zipcode);
        entity.setCity(city);
        entity.setUserId(userId);
        entity.setCompanyId(companyId);

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(address, entity.getAddress());
        assertEquals(zipcode, entity.getZipcode());
        assertEquals(city, entity.getCity());
        assertEquals(userId, entity.getUserId());
        assertEquals(companyId, entity.getCompanyId());
    }

}
