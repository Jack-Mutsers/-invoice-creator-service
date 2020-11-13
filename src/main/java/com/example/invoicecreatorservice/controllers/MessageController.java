package com.example.invoicecreatorservice.controllers;

import com.example.invoicecreatorservice.data_transfer_objects.*;
import com.example.invoicecreatorservice.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/messenger")
public class MessageController {
    @Autowired
    private final MessageService service = new MessageService();

    @GetMapping(path="/notifications/{contactCode}")
    public @ResponseBody ResponseEntity<Object> getNotifications(@PathVariable String contactCode) {
        Iterable<MessageDTO> messages = service.getNotifications(contactCode);

        if(messages == null){
            return new ResponseEntity<>("No Notifications could be found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping(path="/outgoing/{id}")
    public @ResponseBody ResponseEntity<Object> getOutgoingRequests(@PathVariable int id) {
        Iterable<MessageDTO> messages = service.getOutgoingRequests(id);

        if(messages == null){
            return new ResponseEntity<>("No outgoing requests could be found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping(path="/incomming/{contactCode}")
    public @ResponseBody ResponseEntity<Object> getIncomingRequests(@PathVariable String  contactCode) {
        Iterable<MessageDTO> messages = service.getIncomingRequests(contactCode);

        if(messages == null){
            return new ResponseEntity<>("No incoming requests could be found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<Object> createMessage(@RequestBody MessageForAlterationDTO messageDTO) {
        if(messageDTO.validateForCreation()){
            return new ResponseEntity<>("Please provide valid data for the creation", HttpStatus.CONFLICT);
        }

        boolean success = service.createMessage(messageDTO);

        if(!success){
            return new ResponseEntity<>("Something went wrong with the creation of the message.", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("Message has been send", HttpStatus.OK);
    }

    @PutMapping(path="")
    public @ResponseBody ResponseEntity<Object> updateRequest(@RequestBody MessageForAlterationDTO messageDTO){
        if(messageDTO.validateForUpdate()){
            return new ResponseEntity<>("Please provide valid data for the update", HttpStatus.CONFLICT);
        }

        boolean success = service.updateRequest(messageDTO);

        if(!success){
            return  new ResponseEntity<>("Something went wrong with the update of the message.", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("Request has been updated", HttpStatus.OK);
    }
}
