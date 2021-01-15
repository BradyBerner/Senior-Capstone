package com.gcu.conversationservice.service;

import com.gcu.conversationservice.business.ConversationBusinessInterface;
import com.gcu.conversationservice.model.ConversationModel;
import com.gcu.conversationservice.utility.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conversationservice")
public class ConversationRestService {

    private ConversationBusinessInterface<ConversationModel> conversationService;

    @Autowired
    public void setConversationBusinessService(ConversationBusinessInterface<ConversationModel> conversationService){
        this.conversationService = conversationService;
    }

    @PostMapping(path = "/getConversation", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getConversation(@RequestBody ConversationModel conversation){
        try{
            //Try and find a conversation by it's ID and return the complete ConversationModel along with HTTP Status 200 if successful
            return new ResponseEntity<>(conversationService.findByID(conversation), HttpStatus.OK);
        } catch (ItemNotFoundException e){
            //Return a HTTP status code of 204 if no results for the ID were found
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            //Return a HTTP status code of 500 if an error occurred
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> create(@RequestBody ConversationModel conversation){
        try{
            //Try and create a new conversation entry in the database and return the complete ConversationModel along with HTTP Status 200 if successful
            return new ResponseEntity<>(conversationService.create(conversation), HttpStatus.OK);
        } catch (ItemNotFoundException e){
            //Return a HTTP status code of 406 if the data entered is not valid
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e){
            //Return a HTTP status code of 500 if an error occurred
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/leave", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> leave(@RequestBody ConversationModel conversation){
        try{
            //Try and delete a conversation record from the database and return a boolean of the result as well as a HTTP status code of 200
            return new ResponseEntity<>(conversationService.leave(conversation), HttpStatus.OK);
        } catch (Exception e){
            //Return a HTTP status code of 500 if an error occured
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
