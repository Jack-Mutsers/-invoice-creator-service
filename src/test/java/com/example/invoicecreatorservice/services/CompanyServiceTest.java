package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.objects.data_transfer_objects.CompanyDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.CompanyForAlterationDTO;
import com.example.invoicecreatorservice.objects.models.Company;
import com.example.invoicecreatorservice.repositories.CompanyRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
class CompanyServiceTest {

    @Mock
    CompanyRepo repo;

    @InjectMocks
    CompanyService service;

    private final List<Company> entityList = new ArrayList<>();

    private CompanyServiceTest(){
        entityList.add(new Company(1, "Praxis" , "St.Cathrinalane 45", "1346 CF","Amsterdam","0612345678","1Dfr23AS2d",11));
        entityList.add(new Company(2, "plumbing service eindhoven"  , "Sterrenlaan 146", "9654 BV","Eindhoven","0623456789","sd3Fv354Sf",12));
        entityList.add(new Company(3, "Mediamarkt" , "Kerkstraat 2", "1967 EW","Helmond","0634567890","69Feg34Cdf",13));
        entityList.add(new Company(4, "Philips" , "Titanusstraat 97", "2345 TG","Asten","0645678901","oPsdf34Fs3",14));
        entityList.add(new Company(5, "Henks funriture" , "testlane 64", "1234 AB","Testvile","0656789012","P2fS52sFds",15));
    }

    @Test
    void getCompanyTest(){
        //Arrange
        Company entity = this.entityList.get(4);

        //Prepare overwrites
        when(repo.findById(entity.getId())).thenReturn(entity);

        //Act
        CompanyDTO resultEntity = service.getCompany(entity.getId());
        CompanyDTO expectedEntity = new CompanyDTO(entity);

        //Assert
        assertEquals(expectedEntity.getName(), resultEntity.getName());
        assertEquals(expectedEntity.getAddress(), resultEntity.getAddress());
        assertEquals(expectedEntity.getZipcode(), resultEntity.getZipcode());
        assertEquals(expectedEntity.getCity(), resultEntity.getCity());
        assertEquals(expectedEntity.getTelephoneNumber(), resultEntity.getTelephoneNumber());
        assertEquals(expectedEntity.getContactCode(), resultEntity.getContactCode());
        assertEquals(expectedEntity.getOwnerId(), resultEntity.getOwnerId());
    }

    @Test
    void getAllCompaniesTest(){
        //Arrange

        //Prepare overwrites
        when(repo.findAll()).thenReturn(entityList);

        //Act
        List<CompanyDTO> resultEntity = (List) service.getAllCompanies();

        //Assert
        assertEquals(entityList.size(), resultEntity.size());
    }

    @Test
    void deleteCompanyTestSuccess(){
        //Arrange
        Company entity = entityList.get(2);

        //Prepare overwrites
        when(repo.findByIdAndOwnerId(entity.getId(), entity.getOwnerId())).thenReturn(entity);

        //Act
        boolean result = service.deleteCompany(entity.getId(), entity.getOwnerId());

        //Assert
        assertTrue(result);
    }

    @Test
    void deleteCompanyTestCompanyDoesNotExistFailure(){
        //Arrange
        Company entity = entityList.get(2);

        //Prepare overwrites
        when(repo.findByIdAndOwnerId(entity.getId(), entity.getOwnerId())).thenReturn(null);

        //Act
        Boolean result = service.deleteCompany(entity.getId(), entity.getOwnerId());

        //Assert
        assertFalse(result);
    }

    @Test
    void deleteCompanyTestExceptionFailure(){
        //Arrange
        Company entity = entityList.get(2);

        //Prepare overwrites
        when(repo.findByIdAndOwnerId(entity.getId(), entity.getOwnerId())).thenReturn(entity);
        doThrow(new EmptyResultDataAccessException(1000)).when(repo).deleteById(notNull());

        //Act
        Boolean result = service.deleteCompany(entity.getId(), entity.getOwnerId());

        //Assert
        assertFalse(result);
    }

    @Test
    void createCompanyTestSuccess(){
        //Arrange
        Company entity = entityList.get(3);
        CompanyForAlterationDTO entityDTO = new CompanyForAlterationDTO(
            0,
            entity.getName(),
            entity.getAddress(),
            entity.getZipcode(),
            entity.getCity(),
            entity.getTelephoneNumber(),
            entity.getContactCode(),
            entity.getOwnerId()
        );
        int userId = 14;

        //Prepare overwrites
        when(repo.save((Company)notNull())).thenReturn(entity);

        //Act
        Company resultEntity = service.createCompany(entityDTO, userId);

        //Assert
        assertEquals(entity.getName(), resultEntity.getName());
        assertEquals(entity.getAddress(), resultEntity.getAddress());
        assertEquals(entity.getZipcode(), resultEntity.getZipcode());
        assertEquals(entity.getCity(), resultEntity.getCity());
        assertEquals(entity.getTelephoneNumber(), resultEntity.getTelephoneNumber());
        assertEquals(entity.getContactCode(), resultEntity.getContactCode());
        assertEquals(entity.getOwnerId(), resultEntity.getOwnerId());
    }

    @Test
    void createCompanyTestExceptionFailure(){
        //Arrange
        int userId = 14;

        //Prepare overwrites
        when(repo.save((Company)isNull())).thenThrow(new IllegalArgumentException("Target object must not be null"));

        //Act
        Company resultEntity = service.createCompany(null, userId);

        //Assert
        assertNull(resultEntity);
    }

    @Test
    void UpdateCompanyTestSuccess(){
        //Arrange
        Company entity = entityList.get(3);
        entity.setName("Karel");
        CompanyForAlterationDTO entityDTO = new CompanyForAlterationDTO(
                4,
                entity.getName(),
                entity.getAddress(),
                entity.getZipcode(),
                entity.getCity(),
                entity.getTelephoneNumber(),
                entity.getContactCode(),
                entity.getOwnerId()
        );
        int userId = 13;

        //Prepare overwrites
        when(repo.save((Company)notNull())).thenReturn(entity);

        //Act
        CompanyDTO result = service.updateCompany(entityDTO);

        //Assert
        assertNotNull(result);
    }

    @Test
    void UpdateCompanyTestFailure(){
        //Arrange
        Company entity = entityList.get(3);
        CompanyForAlterationDTO entityDTO = new CompanyForAlterationDTO(
            0,
            entity.getName(),
            entity.getAddress(),
            "",
            entity.getCity(),
            entity.getTelephoneNumber(),
            entity.getContactCode(),
            entity.getOwnerId()
        );
        int userId = 16;

        //Prepare overwrites
        when(repo.save((Company)notNull())).thenThrow(new NullPointerException("zipcode cannot be empty"));

        //Act
        CompanyDTO result = service.updateCompany(entityDTO);

        //Assert
        assertNotNull(result);
    }
}
