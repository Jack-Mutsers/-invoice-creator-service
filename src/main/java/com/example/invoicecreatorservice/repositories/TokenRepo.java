package com.example.invoicecreatorservice.repositories;

import com.example.invoicecreatorservice.objects.models.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepo extends CrudRepository<Token, Integer> {
    Token findById(int id);
    Token findByJwtTokenAndIp(String token, String ip);
}
