package com.example.invoicecreatorservice.models;

import com.example.invoicecreatorservice.data_transfer_objects.CustomerForCreationDTO;
import com.example.invoicecreatorservice.data_transfer_objects.CustomerForUpdateDTO;
import com.example.invoicecreatorservice.data_transfer_objects.MessageForCreationDTO;
import com.example.invoicecreatorservice.data_transfer_objects.MessageForUpdateDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("WeakerAccess")
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int userId;
    private String contactCode;
    private String message;
    private String type;

    public Message(int id, int userId, String contactCode, String message, String type){
        this.id = id;
        this.userId = userId;
        this.contactCode = contactCode;
        this.message = message;
        this.type = type;
    }

    public Message(MessageForCreationDTO messageDTO) {
        this.id = 0;
        this.userId = messageDTO.getUserId();
        this.contactCode = messageDTO.getContactCode();
        this.message = messageDTO.getMessage();
        this.type = messageDTO.getType();
    }

    public Message(MessageForUpdateDTO messageDTO) {
        this.id = messageDTO.getId();
        this.userId = messageDTO.getUserId();
        this.contactCode = messageDTO.getContactCode();
        this.message = messageDTO.getMessage();
        this.type = messageDTO.getType();
    }

    public Message(){

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
