package com.example.invoicecreatorservice.data_transfer_objects;

import com.example.invoicecreatorservice.models.UserAccount;

public class UserAccountDTO {
    private int id;
    private String username;
    private UserDTO userDTO;

    public UserAccountDTO(int id, String username, UserDTO userDTO) {
        this.id = id;
        this.username = username;
        this.userDTO = userDTO;
    }

    public UserAccountDTO(UserAccount userAccount) {
        this.id = userAccount.getId();
        this.username = userAccount.getUsername();
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

    public UserDTO getUser() {
        return userDTO;
    }

    public void setUser(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

}
