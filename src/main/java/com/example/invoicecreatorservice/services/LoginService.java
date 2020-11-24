package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.objects.data_transfer_objects.UserAccountDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.UserAccountForAlterationDTO;
import com.example.invoicecreatorservice.objects.models.UserAccount;
import com.example.invoicecreatorservice.repositories.UserAccountRepo;
import com.example.invoicecreatorservice.repositories.UserSessionsRepo;
import com.example.invoicecreatorservice.helpers.tools.BCryptEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserAccountRepo userAccountRepo;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserSessionsRepo sessionsRepo;

    private BCryptEncoder encoder = BCryptEncoder.getInstance();

    public UserAccountDTO login(UserAccountForAlterationDTO account) {

        try {
            companyService = new CompanyService();

            UserAccount userAccount = userAccountRepo.findByUsername(account.getUsername());

            if (userAccount != null) {
                boolean validPassword = encoder.validatePassword(account.getPassword(), userAccount.getPassword());

                if (validPassword) {
                    UserAccountDTO accountDTO = new UserAccountDTO(userAccount);

                    if (accountDTO.getCompany() != null && accountDTO.getCompany().getId() > 0) {
                        accountDTO.setCompany(companyService.getCompany(accountDTO.getCompany().getId()));
                    }


                    return accountDTO;
                }
            }

            return null;
        }
        catch (Exception ex){
            return null;
        }
    }
}
