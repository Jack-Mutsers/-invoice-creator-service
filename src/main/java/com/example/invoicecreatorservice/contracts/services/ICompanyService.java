package com.example.invoicecreatorservice.contracts.services;

import com.example.invoicecreatorservice.objects.data_transfer_objects.CompanyDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.CompanyForAlterationDTO;
import com.example.invoicecreatorservice.objects.models.Company;

public interface ICompanyService {
    CompanyDTO getCompany(int companyId);
    Iterable<CompanyDTO> getAllCompanies();
    boolean deleteCompany(int companyId, int userId);
    Company createCompany(CompanyForAlterationDTO companyDTO, int userId);
    boolean updateCompany(CompanyForAlterationDTO companyDTO);
}
