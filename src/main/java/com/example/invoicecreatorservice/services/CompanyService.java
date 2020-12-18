package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.contracts.services.ICompanyService;
import com.example.invoicecreatorservice.helpers.logger.LoggerService;
import com.example.invoicecreatorservice.objects.data_transfer_objects.CompanyDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.CompanyForAlterationDTO;
import com.example.invoicecreatorservice.objects.models.Company;
import com.example.invoicecreatorservice.repositories.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.example.invoicecreatorservice.helpers.tools.Helper.emptyIfNull;

@Service
public class CompanyService implements ICompanyService {

    @Autowired
    private CompanyRepo companyRepo;

    public CompanyDTO getCompany(int companyId){
        if(companyId == 0){return new CompanyDTO();}

        Company company = companyRepo.findById(companyId);
        return new CompanyDTO(company);
    }

    public Iterable<CompanyDTO> getAllCompanies(){
        List<Company> companies = (List) companyRepo.findAll();
        return this.convertListToDTO(companies);
    }

    @Transactional
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

            return companyRepo.save(company);
        }catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return null;
        }
    }

    public CompanyDTO updateCompany(CompanyForAlterationDTO companyDTO) {
        try {
            Company company = new Company(companyDTO);
            companyRepo.save(company);
            return new CompanyDTO(company);
        }catch (Exception ex){
            return null;
        }
    }

    private List<CompanyDTO> convertListToDTO(List<Company> list){
        List<CompanyDTO> companyDTOS = new ArrayList<>();

        for(Company company : emptyIfNull(list)){
            companyDTOS.add(new CompanyDTO(company));
        }

        return companyDTOS;
    }
}
