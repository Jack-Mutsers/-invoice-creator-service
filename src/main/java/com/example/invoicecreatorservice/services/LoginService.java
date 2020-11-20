package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.data_transfer_objects.UserAccountDTO;
import com.example.invoicecreatorservice.data_transfer_objects.UserAccountForAlterationDTO;
import com.example.invoicecreatorservice.models.UserAccount;
import com.example.invoicecreatorservice.models.UserSession;
import com.example.invoicecreatorservice.repositories.UserAccountRepo;
import com.example.invoicecreatorservice.repositories.UserSessionsRepo;
import com.example.invoicecreatorservice.tools.BCryptEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

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

//                    accountDTO.setToken(this.startSessions(accountDTO.getId()));

                    return accountDTO;
                }
            }

            return null;
        }
        catch (Exception ex){
            return null;
        }
    }

    private UUID startSessions(int userID){
        UserSession session = new UserSession(userID);
        sessionsRepo.save(session);

        return session.getToken();
    }

    private boolean validateSession(int sessionsId) {
        try {
            UserSession session = sessionsRepo.findById(sessionsId);

            if(session == null){
                return false;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            Date date = sdf.parse(session.getExpirationDate());

            String currentDateTimeString = sdf.format(new Date());
            Date currentDatetime = sdf.parse(currentDateTimeString);

            return date.after(currentDatetime);

        }catch (ParseException ex){
            return false;
        }
    }
}
