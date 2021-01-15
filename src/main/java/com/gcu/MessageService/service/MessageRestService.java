package com.gcu.MessageService.service;

import com.gcu.MessageService.business.MessageBusinessInterface;
import com.gcu.MessageService.model.ConversationModel;
import com.gcu.MessageService.model.MessageModel;
import com.gcu.MessageService.utility.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messageservice")
public class MessageRestService {

    private MessageBusinessInterface<MessageModel> messageService;

    @Autowired
    public void setMessageBusinessService(MessageBusinessInterface<MessageModel> messageService){
        this.messageService = messageService;
    }

    @PostMapping(path = "/view", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> view(@RequestBody ConversationModel conversation){
        try{
            //Try and get all of the messages contained within a conversation and return them all along with a HTTP status code of 200 if successful
            return new ResponseEntity<>(messageService.view(conversation), HttpStatus.OK);
        } catch (ItemNotFoundException e){
            //Return a HTTP status code of 204 in the event that there are no messages in the conversation
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            //Return a HTTP status code of 500 in the event that an error occurs
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/send", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> send(@RequestBody MessageModel message){
        try{
            //Try and create a new message record and return the complete MessageModel along with a HTTP status code of 200 if successful
            return new ResponseEntity<>(messageService.send(message), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            //TODO implement data validation
            //Return a HTTP status code of 406 if the message sent is not valid
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e){
            //Return a HTTP status code of 500 in the event that an error occurs
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> edit(@RequestBody MessageModel message){
        try{
            //Try and update an existing message and return the updated MessageModel and a HTTP status code of 200 if successful
            return new ResponseEntity<>(messageService.edit(message), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            //TODO implement data validation
            //Return a HTTP status code of 406 if the message edited is not valid
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e){
            //Return a HTTP status code of 500 in the event that an error occurs
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@RequestBody MessageModel message){
        try{
            //Try and delete a message record from the database and return a boolean and a HTTP status code of 200
            return new ResponseEntity<>(messageService.delete(message), HttpStatus.OK);
        } catch (Exception e){
            //Return a HTTP status code of 500 in the event that an error occurs
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
