package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.objects.models.Token;
import com.example.invoicecreatorservice.repositories.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.invoicecreatorservice.helpers.tools.Helper.validateStringValue;

@Service
public class JwtTokenService {

    @Autowired
    private TokenRepo tokenRepo;

    public boolean validateToken(String jwtToken, String ip){
        Token token = tokenRepo.findByJwtTokenAndIp(jwtToken, ip);

        return token != null && token.isValid();
    }

    public boolean addNewToken(String jwtToken, String ip){
        if(validateStringValue(jwtToken) || validateStringValue(ip)){
            return false;
        }

        Token token = new Token(jwtToken, ip, true);
        tokenRepo.save(token);

        return true;
    }

    public boolean setExpiredToken(String jwtToken, String ip){
        if(validateStringValue(jwtToken) || validateStringValue(ip)){
            return false;
        }

        Token token = tokenRepo.findByJwtTokenAndIp(jwtToken, ip);
        token.setValid(false);

        tokenRepo.save(token);

        return true;
    }

}
