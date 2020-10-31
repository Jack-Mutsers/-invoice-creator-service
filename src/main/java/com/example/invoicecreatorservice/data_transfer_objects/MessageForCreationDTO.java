package com.example.invoicecreatorservice.data_transfer_objects;

public class MessageForCreationDTO {
    private int userId;
    private String contactCode;
    private String message;
    private String type;

    public MessageForCreationDTO(int userId, String contactCode, String message, String type){
        this.userId = userId;
        this.contactCode = contactCode;
        this.message = message;
        this.type = type;
    }

    public MessageForCreationDTO(){

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
