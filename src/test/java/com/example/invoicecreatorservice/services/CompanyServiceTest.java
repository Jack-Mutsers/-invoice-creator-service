package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.data_transfer_objects.CompanyDTO;
import com.example.invoicecreatorservice.data_transfer_objects.UserAccountDTO;
import com.example.invoicecreatorservice.models.Company;
import com.example.invoicecreatorservice.models.UserAccount;
import com.example.invoicecreatorservice.repositories.CompanyRepo;
import com.example.invoicecreatorservice.repositories.UserAccountRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CompanyServiceTest {

    @Mock
    CompanyRepo repo;

    @InjectMocks
    CompanyService service;

    @Test
    void getCompanyTest(){
        //Arrange
        int companyId = 5;
        String name = "henk";
        String address = "testlane 64";
        String zipcode = "1234 AB";
        String city = "Testvile";
        String telephoneNumber = "0612345678";
        String contactCode = "1Dfr23AS2d";
        int ownerId = 16;

        Company entity = new Company(
                companyId,
                name,
                address,
                zipcode,
                city,
                telephoneNumber,
                contactCode,
                ownerId
        );

        String username = "henk";
        String password = "Password1!";
        int userId = 5;
        String userContactCode = "1234567890";

        UserAccount userEntity = new UserAccount(
                ownerId,
                username,
                password,
                userId,
                userContactCode,
                companyId
        );

        when(repo.findById(companyId)).thenReturn(entity);

        //Act
        CompanyDTO resultEntity = service.getCompany(companyId);
        CompanyDTO expectedEntity = new CompanyDTO(entity);

        //Assert
        assertEquals(expectedEntity.getId(), resultEntity.getId());
        assertEquals(expectedEntity.getName(), resultEntity.getName());
        assertEquals(expectedEntity.getAddress(), resultEntity.getAddress());
        assertEquals(expectedEntity.getZipcode(), resultEntity.getZipcode());
        assertEquals(expectedEntity.getCity(), resultEntity.getCity());
        assertEquals(expectedEntity.getTelephoneNumber(), resultEntity.getTelephoneNumber());
        assertEquals(expectedEntity.getContactCode(), resultEntity.getContactCode());
        assertEquals(expectedEntity.getOwnerId(), resultEntity.getOwnerId());
    }
}
