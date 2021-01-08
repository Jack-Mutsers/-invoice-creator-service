package com.example.invoicecreatorservice.objects.data_transfer_objects;

import lombok.Getter;

import static com.example.invoicecreatorservice.helpers.tools.Helper.validateStringValue;

@Getter
public class MessageForAlterationDTO {
    private int id;
    private int userId;
    private String contactCode;
    private String messageBody;
    private String type;
    private Boolean done;

    public MessageForAlterationDTO(int id, int userId, String contactCode, String messageBody, String type, boolean done){
        this.id = id;
        this.userId = userId;
        this.contactCode = contactCode;
        this.messageBody = messageBody;
        this.type = type;
        this.done = done;
    }

    public MessageForAlterationDTO(){}

    public boolean validateForUpdate(){
        return (this.id == 0 || this.validateForCreation());
    }

    public boolean validateForCreation(){
        return (this.userId == 0 || validateStringValue(this.contactCode) || validateStringValue(this.messageBody) || validateStringValue(this.type));
    }

    public void setId(int id) {
        this.id = id;
    }

}
