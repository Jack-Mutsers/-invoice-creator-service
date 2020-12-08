package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.objects.models.Token;
import com.example.invoicecreatorservice.repositories.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenService {

    @Autowired
    private TokenRepo tokenRepo;

    public boolean validateToken(String jwtToken, String ip){
        Token token = tokenRepo.findByJwtTokenAndIp(jwtToken, ip);

        return token != null && token.isValid();
    }

    public void addNewToken(String jwtToken, String ip){
        Token token = new Token(jwtToken, ip, true);
        tokenRepo.save(token);
    }

    public void setExpiredToken(String jwtToken, String ip){
        Token token = tokenRepo.findByJwtTokenAndIp(jwtToken, ip);
        token.setValid(false);

        tokenRepo.save(token);
    }

}
