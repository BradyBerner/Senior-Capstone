package com.gcu.MessageService.business;

import com.gcu.MessageService.data.DataAccessInterface;
import com.gcu.MessageService.data.entity.MessageEntity;
import com.gcu.MessageService.model.ConversationModel;
import com.gcu.MessageService.model.MessageModel;
import com.gcu.MessageService.utility.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

public class MessageBusinessService implements MessageBusinessInterface<MessageModel> {

    //TODO remove once service discovery is implemented
    private final String conversationServiceIP = "127.0.0.1:8082/conversationservice";
    private DataAccessInterface<MessageEntity> messageService;

    @Autowired
    public void setMessageDataService(DataAccessInterface<MessageEntity> messageService){
        this.messageService = messageService;
    }

    @Override
    public List<MessageModel> view(ConversationModel conversation) {
        //Try and get all of the messages with the same conversation ID
        List<MessageEntity> results = messageService.findAll(conversation.getID());

        //Check if there are any entries in the list
        if(results.size() != 0){
            //Create a new array list to store the complete MessageModels
            ArrayList<MessageModel> returnVal = new ArrayList<>();
            //Iterate over the results array storing the MessageEntities and insert them as MessageModels in the new ArrayList
            for (MessageEntity message : results) {
                returnVal.add(new MessageModel(message));
            }

            //Return the array of all the MessageModels
            return returnVal;
        } else {
            //Throw an item not found exception if there are no messages linked to the conversation
            throw new ItemNotFoundException();
        }
    }

    @Override
    public MessageModel send(MessageModel message) {
        //Check if the conversation the message is meant to be a part of exists
        if(checkConversationID(message.getConversationID())) {
            //Create the new message record in the database and return the complete MessageModel
            return new MessageModel(messageService.create(message.toEntity()));
        } else {
            //Throw an ItemNotFoundException in the event the conversation does not exist
            throw new ItemNotFoundException();
        }
    }

    @Override
    public MessageModel edit(MessageModel message) {
        //Attempt to update an existing database message record
        return new MessageModel(messageService.update(message.toEntity()));
    }

    @Override
    public boolean delete(MessageModel message) {
        //Attempt to remove an existing database message record
        return messageService.delete(message.toEntity());
    }

    private boolean checkConversationID(String conversationID){
        //Create a new RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        try {
            //Create a new ConversationModel and set it's ID to check if the conversation exists
            ConversationModel conversation = new ConversationModel();
            conversation.setID(conversationID);
            //Check with the conversation service if the conversation exists or not
            return restTemplate.exchange("http://"+conversationServiceIP+"/getConversation", HttpMethod.POST, new HttpEntity<>(conversation), ConversationModel.class).getStatusCode() == HttpStatus.OK;
        } catch (Exception e){
            //Throw a new exception in the event that an error occurs
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
