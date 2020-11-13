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

    public MessageForAlterationDTO(){

    }

    public boolean validateForUpdate(){
        return (this.id == 0 || this.validateForCreation());
    }

    public boolean validateForCreation(){
        return (this.userId == 0 || this.contactCode == null || this.messageBody == null || this.type == null ||
                this.contactCode.isBlank() || this.messageBody.isBlank() || this.type.isBlank());
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

    public String getContactCode() {
        return contactCode;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public String getType() {
        return type;
    }

    public boolean getDone(){
        return done;
    }

}
