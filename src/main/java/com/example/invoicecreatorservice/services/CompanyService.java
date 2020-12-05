package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.contracts.services.ICompanyService;
import com.example.invoicecreatorservice.helpers.logger.LoggerService;
import com.example.invoicecreatorservice.objects.data_transfer_objects.CompanyDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.CompanyForAlterationDTO;
import com.example.invoicecreatorservice.objects.models.Company;
import com.example.invoicecreatorservice.repositories.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService implements ICompanyService {

    @Autowired
    private CompanyRepo companyRepo;

    public CompanyDTO getCompany(int companyId){
        Company company = companyRepo.findById(companyId);
        CompanyDTO companyDTO = new CompanyDTO(company);
        companyDTO.setOwnerId(company.getOwnerId());
        return companyDTO;
    }

    public Iterable<CompanyDTO> getAllCompanies(){
        List<Company> companies = (List) companyRepo.findAll();

        if(companies.isEmpty()){ return null; }

        return this.convertListToDTO(companies);
    }

    public boolean deleteCompany(int companyId, int userId) {
        try{
            Company company = companyRepo.findByIdAndOwnerId(companyId, userId);

            if(company == null){
                return false;
            }

            companyRepo.deleteById(company.getId());

            return true;
        }catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return false;
        }
    }

    public Company createCompany(CompanyForAlterationDTO companyDTO, int userId) {
        try{
            Company company = new Company(companyDTO);
            company.setOwnerId(userId);

            // set id to 0 to prevent update of existing record on create
            company.setId(0);

            Company newObject = companyRepo.save(company);
            return newObject;
        }catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return null;
        }
    }

    public boolean updateCompany(CompanyForAlterationDTO companyDTO) {
        try {
            Company company = new Company(companyDTO);
            companyRepo.save(company);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    private List<CompanyDTO> convertListToDTO(List<Company> list){
        List<CompanyDTO> companyDTOS = new ArrayList<>();

        for(Company company : list){
            companyDTOS.add(new CompanyDTO(company));
        }

        return companyDTOS;
    }
}
