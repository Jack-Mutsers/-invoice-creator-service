package com.example.invoicecreatorservice.models;

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

        Customer customer = new Customer(
            id,
            name,
            address,
            zipcode,
            city
        );

        assertEquals(id, customer.getId());
        assertEquals(name, customer.getName());
        assertEquals(address, customer.getAddress());
        assertEquals(zipcode, customer.getZipcode());
        assertEquals(city, customer.getCity());
    }


    @Test
    void instantiateEmptyEntity(){
        int id = 0;
        String name = null;
        String address = null;
        String zipcode = null;
        String city = null;

        Customer customer = new Customer();

        assertEquals(id, customer.getId());
        assertEquals(name, customer.getName());
        assertEquals(address, customer.getAddress());
        assertEquals(zipcode, customer.getZipcode());
        assertEquals(city, customer.getCity());
    }
}
