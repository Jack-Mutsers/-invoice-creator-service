package com.example.invoicecreatorservice.models;

import com.example.invoicecreatorservice.data_transfer_objects.UserAccountForCreationDTO;
import com.example.invoicecreatorservice.data_transfer_objects.UserAccountForUpdateDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("WeakerAccess")
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;
    private int userId;

    public UserAccount(int id, String username, String password, int userId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userId = userId;
    }

    public UserAccount(UserAccountForCreationDTO accountDTO, int userId) {
        this.id = 0;
        this.username = accountDTO.getUsername();
        this.password = accountDTO.getPassword();
        this.userId = userId;
    }

    public UserAccount(UserAccountForUpdateDTO accountDTO) {
        this.id = accountDTO.getId();
        this.username = accountDTO.getUsername();
        this.password = accountDTO.getPassword();
        this.userId = accountDTO.getUserId();
    }

    public UserAccount(){

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
