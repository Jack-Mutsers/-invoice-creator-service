package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserDTOTest {

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

        UserDTO DTOentity = new UserDTO(entity);

        assertEquals(id, DTOentity.getId());
        assertEquals(name, DTOentity.getName());
        assertEquals(address, DTOentity.getAddress());
        assertEquals(zipcode, DTOentity.getZipcode());
        assertEquals(city, DTOentity.getCity());
    }

    @Test
    void instantiateEntityWithContactCode(){
        int id = 2;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "Testvile";
        String contactCode = "Df2dd55EFD";

        User entity = new User(
                id,
                name,
                address,
                zipcode,
                city
        );

        UserDTO DTOentity = new UserDTO(entity, contactCode);

        assertEquals(id, DTOentity.getId());
        assertEquals(name, DTOentity.getName());
        assertEquals(address, DTOentity.getAddress());
        assertEquals(zipcode, DTOentity.getZipcode());
        assertEquals(city, DTOentity.getCity());
        assertEquals(contactCode, DTOentity.getContactCode());
    }


    @Test
    void instantiateEmptyEntity(){
        int id = 0;
        String name = null;
        String address = null;
        String zipcode = null;
        String city = null;

        UserDTO DTOentity = new UserDTO();

        assertEquals(id, DTOentity.getId());
        assertEquals(name, DTOentity.getName());
        assertEquals(address, DTOentity.getAddress());
        assertEquals(zipcode, DTOentity.getZipcode());
        assertEquals(city, DTOentity.getCity());
    }

    @Test
    void fillEmptyEntity(){
        int id = 2;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "Testvile";
        String contactCode = "Df2dd55EFD";

        UserDTO DTOentity = new UserDTO();

        DTOentity.setId(id);
        DTOentity.setName(name);
        DTOentity.setAddress(address);
        DTOentity.setZipcode(zipcode);
        DTOentity.setCity(city);
        DTOentity.setContactCode(contactCode);

        assertEquals(id, DTOentity.getId());
        assertEquals(name, DTOentity.getName());
        assertEquals(address, DTOentity.getAddress());
        assertEquals(zipcode, DTOentity.getZipcode());
        assertEquals(city, DTOentity.getCity());
    }
}
