package com.example.invoicecreatorservice.data_transfer_objects;

public class MessageForUpdateDTO {
    private int id;
    private int userId;
    private String contactCode;
    private String message;
    private String type;

    public MessageForUpdateDTO(int id, int userId, String contactCode, String message, String type){
        this.id = id;
        this.userId = userId;
        this.contactCode = contactCode;
        this.message = message;
        this.type = type;
    }

    public MessageForUpdateDTO(){

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContactCode() {
        return contactCode;
    }

    public void setContactCode(String contactCode) {
        this.contactCode = contactCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
