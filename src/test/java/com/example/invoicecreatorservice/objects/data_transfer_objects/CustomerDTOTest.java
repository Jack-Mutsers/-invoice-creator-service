package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CustomerDTOTest {

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

        CustomerDTO DTOentity = new CustomerDTO(entity);

        assertEquals(id, DTOentity.getId());
        assertEquals(name, DTOentity.getName());
        assertEquals(address, DTOentity.getAddress());
        assertEquals(zipcode, DTOentity.getZipcode());
        assertEquals(city, DTOentity.getCity());
    }

    @Test
    void instantiateEmptyEntity(){
        int id = 0;
        String name = null;
        String address = null;
        String zipcode = null;
        String city = null;

        Customer DTOentity = new Customer();

        assertEquals(id, DTOentity.getId());
        assertEquals(name, DTOentity.getName());
        assertEquals(address, DTOentity.getAddress());
        assertEquals(zipcode, DTOentity.getZipcode());
        assertEquals(city, DTOentity.getCity());
    }

    @Test
    void fillEmptyEntity(){
        int id = 5;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "Testvile";
        int companyId = 1;

        CustomerDTO DTOentity = new CustomerDTO();

        DTOentity.setId(id);
        DTOentity.setName(name);
        DTOentity.setAddress(address);
        DTOentity.setZipcode(zipcode);
        DTOentity.setCity(city);
        DTOentity.setCompanyId(companyId);

        assertEquals(id, DTOentity.getId());
        assertEquals(name, DTOentity.getName());
        assertEquals(address, DTOentity.getAddress());
        assertEquals(zipcode, DTOentity.getZipcode());
        assertEquals(city, DTOentity.getCity());
        assertEquals(companyId, DTOentity.getCompanyId());
    }

}
