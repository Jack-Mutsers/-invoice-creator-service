package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.helpers.logger.LoggerService;
import com.example.invoicecreatorservice.objects.models.JwtUserDetails;
import com.example.invoicecreatorservice.objects.models.UserAccount;
import com.example.invoicecreatorservice.repositories.UserAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAccountRepo userAccountRepo;

    public JwtUserDetails loadUserByUsername(String username){
        try{
            UserAccount user = userAccountRepo.findByUsernameAndActive(username, true);

            if (user == null) {
                throw new UsernameNotFoundException("Not found: " + username);
            }

            return new JwtUserDetails(user);
        } catch (UsernameNotFoundException ex){
            LoggerService.warn(ex.getMessage());
            throw ex;
        }
    }

}
