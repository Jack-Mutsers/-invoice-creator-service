package com.example.invoicecreatorservice.DataTransferObjects;

import com.example.invoicecreatorservice.Models.User;
import com.example.invoicecreatorservice.Models.UserAccount;
import com.example.invoicecreatorservice.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class UserAccountDTO {
    @Autowired
    UserRepo userRepo;

    private int id;
    private String username;
    private User user;

    public UserAccountDTO(int id, String username, int userId) {
        this.id = id;
        this.username = username;
        this.user = userRepo.findById(userId);
    }

    public UserAccountDTO(UserAccount userAccount) {
        this.id = userAccount.getId();
        this.username = userAccount.getUsername();
        this.user = userRepo.findById(userAccount.getUserId());
    }

    public UserAccountDTO(){

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

    public User getUser() {
        return user;
    }

    public void setUser(int userId) {
        this.user = userRepo.findById(userId);
    }

}
