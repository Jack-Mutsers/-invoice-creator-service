package com.example.invoicecreatorservice.models;

import com.example.invoicecreatorservice.data_transfer_objects.CustomerForAlterationDTO;
import com.example.invoicecreatorservice.data_transfer_objects.UserForAlterationDTO;
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


        User entity = new User(
            id,
            name,
            address,
            zipcode,
            city
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

        User entity = new User();

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(address, entity.getAddress());
        assertEquals(zipcode, entity.getZipcode());
        assertEquals(city, entity.getCity());
    }


    @Test
    void instantiateEntityByDTO(){
        int id = 2;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "Testvile";

        UserForAlterationDTO entityDTO = new UserForAlterationDTO(
                id,
                name,
                address,
                zipcode,
                city
        );

        User entity = new User(entityDTO);

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(address, entity.getAddress());
        assertEquals(zipcode, entity.getZipcode());
        assertEquals(city, entity.getCity());
    }

    @Test
    void fillEmptyEntity(){
        int id = 2;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "Testvile";

        User entity = new User();

        entity.setId(id);
        entity.setName(name);
        entity.setAddress(address);
        entity.setZipcode(zipcode);
        entity.setCity(city);

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(address, entity.getAddress());
        assertEquals(zipcode, entity.getZipcode());
        assertEquals(city, entity.getCity());
    }
}
