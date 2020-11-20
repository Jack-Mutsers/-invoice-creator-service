package com.example.invoicecreatorservice.services;

import java.util.Optional;

import com.example.invoicecreatorservice.models.JwtUserDetails;
import com.example.invoicecreatorservice.models.UserAccount;
import com.example.invoicecreatorservice.repositories.UserAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAccountRepo userAccountRepo;

    @Override
    public JwtUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user = userAccountRepo.findByUsernameAndActive(username, true);
        Optional<UserAccount> oUser = Optional.ofNullable(user);

        if (user == null) {
            oUser.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        }

        return new JwtUserDetails(user);
//        UserAccount user = new UserAccount();
//        user.setUsername("John");
//        user.setPassword("pass");
//        user.setActive(true);
//        user.setRole("ROLE_USER");
//        return new JwtUserDetails(user);
    }

}
