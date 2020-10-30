package com.example.invoicecreatorservice.data_transfer_objects;

public class UserAccountForCreationDTO {
    private String username;
    private String password;
    private int userId;

    public UserAccountForCreationDTO(String username, String password, int userId) {
        this.username = username;
        this.password = password;
        this.userId = userId;
    }

    public UserAccountForCreationDTO(){

    }

    public boolean validateUserAccount(){
        return ( this.username == null || this.password == null || this.userId == 0 );
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
