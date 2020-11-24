package com.example.invoicecreatorservice.objects.models;

import com.example.invoicecreatorservice.objects.data_transfer_objects.CompanyForAlterationDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CompanyTest {
    @Test
    void instantiateEntity(){
        int id = 5;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "Testvile";
        String telephoneNumber = "0612345678";
        String contactCode = "1Dfr23AS2d";
        int ownerId = 16;

        Company entity = new Company(
            id,
            name,
            address,
            zipcode,
            city,
            telephoneNumber,
            contactCode,
            ownerId
        );

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(address, entity.getAddress());
        assertEquals(zipcode, entity.getZipcode());
        assertEquals(city, entity.getCity());
        assertEquals(telephoneNumber, entity.getTelephoneNumber());
        assertEquals(contactCode, entity.getContactCode());
        assertEquals(ownerId, entity.getOwnerId());
    }

    @Test
    void instantiateEmptyEntity(){
        int id = 0;
        String name = null;
        String address = null;
        String zipcode = null;
        String city = null;
        String telephoneNumber = null;
        String contactCode = null;
        int ownerId = 0;

        Company entity = new Company();

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(address, entity.getAddress());
        assertEquals(zipcode, entity.getZipcode());
        assertEquals(city, entity.getCity());
        assertEquals(telephoneNumber, entity.getTelephoneNumber());
        assertEquals(contactCode, entity.getContactCode());
        assertEquals(ownerId, entity.getOwnerId());
    }

    @Test
    void instantiateEntityByDTO(){
        int id = 5;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "Testvile";
        String telephoneNumber = "0612345678";
        String contactCode = "1Dfr23AS2d";
        int ownerId = 16;

        CompanyForAlterationDTO entityDTO = new CompanyForAlterationDTO(
            id,
            name,
            address,
            zipcode,
            city,
            telephoneNumber,
            contactCode,
            ownerId
        );

        Company entity = new Company(entityDTO);

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(address, entity.getAddress());
        assertEquals(zipcode, entity.getZipcode());
        assertEquals(city, entity.getCity());
        assertEquals(telephoneNumber, entity.getTelephoneNumber());
        assertEquals(contactCode, entity.getContactCode());
        assertEquals(ownerId, entity.getOwnerId());
    }

    @Test
    void fillEmptyEntity(){
        int id = 5;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "Testvile";
        String telephoneNumber = "0612345678";
        String contactCode = "1Dfr23AS2d";
        int ownerId = 16;

        Company entity = new Company();

        entity.setId(id);
        entity.setName(name);
        entity.setAddress(address);
        entity.setZipcode(zipcode);
        entity.setCity(city);
        entity.setTelephoneNumber(telephoneNumber);
        entity.setContactCode(contactCode);
        entity.setOwnerId(ownerId);

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(address, entity.getAddress());
        assertEquals(zipcode, entity.getZipcode());
        assertEquals(city, entity.getCity());
        assertEquals(telephoneNumber, entity.getTelephoneNumber());
        assertEquals(contactCode, entity.getContactCode());
        assertEquals(ownerId, entity.getOwnerId());
    }
}
