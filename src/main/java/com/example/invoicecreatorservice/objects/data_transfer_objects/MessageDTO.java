package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.Message;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO {
    private int id;
    private int userId;
    private String contactCode;
    private String messageBody;
    private String type;
    private Boolean done;

    public MessageDTO(Message message) {
        this.id = message.getId();
        this.userId = message.getUserId();
        this.contactCode = message.getContactCode();
        this.messageBody = message.getMessageBody();
        this.type = message.getType();
        this.done = message.getDone();
    }

    public MessageDTO(){}

}
