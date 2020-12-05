package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.Company;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CompanyDTOTest {
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

        CompanyDTO DTOentity = new CompanyDTO(entity);

        assertEquals(name, DTOentity.getName());
        assertEquals(address, DTOentity.getAddress());
        assertEquals(zipcode, DTOentity.getZipcode());
        assertEquals(city, DTOentity.getCity());
        assertEquals(telephoneNumber, DTOentity.getTelephoneNumber());
        assertEquals(contactCode, DTOentity.getContactCode());
        assertEquals(ownerId, DTOentity.getOwnerId());
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

        CompanyDTO DTOentity = new CompanyDTO();

        assertEquals(name, DTOentity.getName());
        assertEquals(address, DTOentity.getAddress());
        assertEquals(zipcode, DTOentity.getZipcode());
        assertEquals(city, DTOentity.getCity());
        assertEquals(telephoneNumber, DTOentity.getTelephoneNumber());
        assertEquals(contactCode, DTOentity.getContactCode());
        assertEquals(ownerId, DTOentity.getOwnerId());
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

        CompanyDTO DTOentity = new CompanyDTO();

        DTOentity.setName(name);
        DTOentity.setAddress(address);
        DTOentity.setZipcode(zipcode);
        DTOentity.setCity(city);
        DTOentity.setTelephoneNumber(telephoneNumber);
        DTOentity.setContactCode(contactCode);
        DTOentity.setOwnerId(ownerId);

        assertEquals(name, DTOentity.getName());
        assertEquals(address, DTOentity.getAddress());
        assertEquals(zipcode, DTOentity.getZipcode());
        assertEquals(city, DTOentity.getCity());
        assertEquals(telephoneNumber, DTOentity.getTelephoneNumber());
        assertEquals(contactCode, DTOentity.getContactCode());
        assertEquals(ownerId, DTOentity.getOwnerId());
    }
}
