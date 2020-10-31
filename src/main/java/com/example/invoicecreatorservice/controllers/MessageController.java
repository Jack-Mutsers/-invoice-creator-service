package com.example.invoicecreatorservice.controllers;

import com.example.invoicecreatorservice.data_transfer_objects.*;
import com.example.invoicecreatorservice.models.Customer;
import com.example.invoicecreatorservice.services.CustomerService;
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

    @GetMapping(path="/notifications/{id}")
    public @ResponseBody ResponseEntity<Object> getNotifications(@PathVariable int id) {
        return new ResponseEntity<>("Not implemented yet", HttpStatus.OK);
    }

    @GetMapping(path="/outgoing/{id}")
    public @ResponseBody ResponseEntity<Object> getOutgoingRequests(@PathVariable int id) {
        return new ResponseEntity<>("Not implemented yet", HttpStatus.OK);
    }

    @GetMapping(path="/incomming/{id}")
    public @ResponseBody ResponseEntity<Object> getIncomingRequests(@PathVariable int id) {
        return new ResponseEntity<>("Not implemented yet", HttpStatus.OK);
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<Object> createMessage(@RequestBody MessageForCreationDTO messageDTO) {
        return new ResponseEntity<>("Not implemented yet", HttpStatus.OK);
    }

    @PutMapping(path="")
    public @ResponseBody ResponseEntity<Object> UpdateRequest(@RequestBody MessageForUpdateDTO messageDTO){
        return new ResponseEntity<>("Not implemented yet", HttpStatus.OK);
    }
}
