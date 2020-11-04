package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.data_transfer_objects.CompanyDTO;
import com.example.invoicecreatorservice.data_transfer_objects.CompanyForAlterationDTO;
import com.example.invoicecreatorservice.data_transfer_objects.UserAccountDTO;
import com.example.invoicecreatorservice.models.Company;
import com.example.invoicecreatorservice.repositories.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private UserAccountService userAccountService = new UserAccountService();

    public CompanyDTO getCompany(int companyId, int userId){
        UserAccountDTO accountDTO = userAccountService.getUserAccount(userId);

        if(accountDTO.getCompanyId() != companyId){
            return null;
        }

        Company company = companyRepo.findById(companyId);
        return new CompanyDTO(company);
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
            return false;
        }
    }

    public CompanyDTO createCompany(CompanyForAlterationDTO companyDTO, int userId) {
        if(companyDTO.validateForCreation()){
            return null;
        }
        if(companyDTO.getContactCode() == null){
            companyDTO.generateContactCode();
        }

        try{
            Company company = new Company(companyDTO);
            company.setOwnerId(userId);
            Company newObject = companyRepo.save(company);
            return new CompanyDTO(newObject);
        }catch (Exception ex){
            return null;
        }
    }

    public boolean updateCompany(CompanyForAlterationDTO companyDTO, int userId) {
        if(companyDTO.validateForUpdate()){
            return false;
        }

        try {
            UserAccountDTO accountDTO = userAccountService.getUserAccount(userId);

            if(accountDTO.getCompanyId() != companyDTO.getId()){
                return false;
            }

            Company company = new Company(companyDTO);
            companyRepo.save(company);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}
