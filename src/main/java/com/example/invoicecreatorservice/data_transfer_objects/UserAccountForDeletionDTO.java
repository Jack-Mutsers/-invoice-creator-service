package com.example.invoicecreatorservice.data_transfer_objects;

public class UserAccountForDeletionDTO {
    private int id;
    private String username;
    private String password;
    private int userId;

    public UserAccountForDeletionDTO(int id, String username, String password, int userId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userId = userId;
    }

    public UserAccountForDeletionDTO(){

    }

    public boolean validateUserAccount(){
        return !( this.id == 0 || this.username == null || this.password == null || this.userId == 0 );
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
