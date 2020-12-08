package com.example.invoicecreatorservice.objects.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String jwtToken;
    private String ip;
    private boolean valid;

    public Token(int id, String token, String ip, boolean valid) {
        this.id = id;
        this.jwtToken = token;
        this.ip = ip;
        this.valid = valid;
    }

    public Token(String token, String ip, boolean valid) {
        this.jwtToken = token;
        this.ip = ip;
        this.valid = valid;
    }

    public Token() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return jwtToken;
    }

    public void setToken(String token) {
        this.jwtToken = token;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
