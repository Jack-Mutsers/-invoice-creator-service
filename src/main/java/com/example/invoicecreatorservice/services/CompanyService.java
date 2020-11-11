package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.data_transfer_objects.CompanyDTO;
import com.example.invoicecreatorservice.data_transfer_objects.CompanyForAlterationDTO;
import com.example.invoicecreatorservice.data_transfer_objects.UserAccountDTO;
import com.example.invoicecreatorservice.models.Company;
import com.example.invoicecreatorservice.repositories.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {
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
            CompanyDTO newObjectDTO = new CompanyDTO(newObject);
            newObjectDTO.setOwnerId(userId);
            return newObjectDTO;
        }catch (Exception ex){
            return null;
        }
    }

    public boolean updateCompany(CompanyForAlterationDTO companyDTO) {
        if(companyDTO.validateForUpdate()){
            return false;
        }

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
