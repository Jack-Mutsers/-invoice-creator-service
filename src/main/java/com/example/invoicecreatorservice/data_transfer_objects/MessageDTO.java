package com.example.invoicecreatorservice.data_transfer_objects;

import com.example.invoicecreatorservice.models.Message;

public class MessageDTO {
    private int id;
    private int userId;
    private String contactCode;
    private String message;
    private String type;

    public MessageDTO(int id, int userId, String contactCode, String message, String type){
        this.id = id;
        this.userId = userId;
        this.contactCode = contactCode;
        this.message = message;
        this.type = type;
    }

    public MessageDTO(Message message) {
        this.id = message.getId();
        this.userId = message.getUserId();
        this.contactCode = message.getContactCode();
        this.message = message.getMessage();
        this.type = message.getType();
    }

    public MessageDTO(){

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
