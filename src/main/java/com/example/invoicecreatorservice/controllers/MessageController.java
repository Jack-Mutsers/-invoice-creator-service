package com.example.invoicecreatorservice.controllers;

import com.example.invoicecreatorservice.contracts.services.IMessageService;
import com.example.invoicecreatorservice.objects.data_transfer_objects.*;
import com.example.invoicecreatorservice.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/messenger")
public class MessageController extends BaseController {
    @Autowired
    private final IMessageService service = new MessageService();

    @GetMapping(path="/notifications/{contactCode}")
    public @ResponseBody ResponseEntity<ResponseDTO> getNotifications(@PathVariable String contactCode) {
        Iterable<MessageDTO> messages = service.getNotifications(contactCode);

        if(messages == null){
            return new ResponseEntity<>(new ResponseDTO(false, "No Notifications could be found"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseDTO(true, messages), HttpStatus.OK);
    }

    @GetMapping(path="/outgoing/{id}")
    public @ResponseBody ResponseEntity<ResponseDTO> getOutgoingRequests(@PathVariable int id) {
        Iterable<MessageDTO> messages = service.getOutgoingRequests(id);

        if(messages == null){
            return new ResponseEntity<>(new ResponseDTO(false, "No outgoing requests could be found"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseDTO(true, messages), HttpStatus.OK);
    }

    @GetMapping(path="/incomming/{contactCode}")
    public @ResponseBody ResponseEntity<ResponseDTO> getIncomingRequests(@PathVariable String  contactCode) {
        Iterable<MessageDTO> messages = service.getIncomingRequests(contactCode);

        if(messages == null){
            return new ResponseEntity<>(new ResponseDTO(false, "No incoming requests could be found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseDTO(true, messages), HttpStatus.OK);
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<ResponseDTO> createMessage(@RequestBody MessageForAlterationDTO messageDTO) {
        if(messageDTO.validateForCreation()){
            return new ResponseEntity<>(new ResponseDTO(false, "Please provide valid data for the creation"), HttpStatus.CONFLICT);
        }

        boolean success = service.createMessage(messageDTO);

        if(!success){
            return new ResponseEntity<>(new ResponseDTO(false, "Something went wrong with the creation of the message."), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(new ResponseDTO(true, "Message has been send"), HttpStatus.OK);
    }

    @PutMapping(path="")
    public @ResponseBody ResponseEntity<ResponseDTO> updateRequest(@RequestBody MessageForAlterationDTO messageDTO){
        if(messageDTO.validateForUpdate()){
            return new ResponseEntity<>(new ResponseDTO(false, "Please provide valid data for the update"), HttpStatus.CONFLICT);
        }

        boolean success = service.updateRequest(messageDTO);

        if(!success){
            return  new ResponseEntity<>(new ResponseDTO(false, "Something went wrong with the update of the message."), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(new ResponseDTO(true, "Request has been updated"), HttpStatus.OK);
    }
}
