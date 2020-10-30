package com.example.invoicecreatorservice.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserTest {

    @Test
    void instantiateEntity(){
        int id = 2;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "Testvile";

        User user = new User(
                id,
                name,
                address,
                zipcode,
                city
        );

        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(address, user.getAddress());
        assertEquals(zipcode, user.getZipcode());
        assertEquals(city, user.getCity());
    }


    @Test
    void instantiateEmptyEntity(){
        int id = 0;
        String name = null;
        String address = null;
        String zipcode = null;
        String city = null;

        User user = new User();

        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(address, user.getAddress());
        assertEquals(zipcode, user.getZipcode());
        assertEquals(city, user.getCity());
    }
}
