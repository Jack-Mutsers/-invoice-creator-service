package com.example.invoicecreatorservice.models;

import com.example.invoicecreatorservice.data_transfer_objects.MessageForAlterationDTO;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private String contactCode;
    private String messageBody;
    private String type;
    private boolean done;

    public Message(int id, int userId, String contactCode, String messageBody, String type, boolean done){
        this.id = id;
        this.userId = userId;
        this.contactCode = contactCode;
        this.messageBody = messageBody;
        this.type = type;
        this.done = done;
    }

    public Message(MessageForAlterationDTO messageDTO) {
        this.id = messageDTO.getId();
        this.userId = messageDTO.getUserId();
        this.contactCode = messageDTO.getContactCode();
        this.messageBody = messageDTO.getMessageBody();
        this.type = messageDTO.getType();
        this.done = messageDTO.getDone();
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

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getDone(){
        return done;
    }

    public void setDone(boolean done){
        this.done = done;
    }
}
