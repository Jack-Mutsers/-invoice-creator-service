package com.example.invoicecreatorservice.data_transfer_objects;

public class MessageForAlterationDTO {
    private int id;
    private int userId;
    private String contactCode;
    private String messageBody;
    private String type;
    private boolean done;

    public MessageForAlterationDTO(int id, int userId, String contactCode, String messageBody, String type, boolean done){
        this.id = id;
        this.userId = userId;
        this.contactCode = contactCode;
        this.messageBody = messageBody;
        this.type = type;
        this.done = done;
    }

    public MessageForAlterationDTO(int userId, String contactCode, String messageBody, String type){
        this.userId = userId;
        this.contactCode = contactCode;
        this.messageBody = messageBody;
        this.type = type;
    }

    public MessageForAlterationDTO(){

    }

    public boolean validateForUpdate(){
        return (this.id == 0 || this.userId == 0 || this.contactCode == null || this.messageBody == null || this.type == null);
    }

    public boolean validateForCreation(){
        return (this.userId == 0 || this.contactCode == null || this.messageBody == null || this.type == null);
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
