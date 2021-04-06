package com.gcu.conversationservice.business;

import com.gcu.conversationservice.data.DataAccessInterface;
import com.gcu.conversationservice.data.entity.ConversationEntity;
import com.gcu.conversationservice.model.ConversationModel;
import com.gcu.conversationservice.model.UserModel;
import com.gcu.conversationservice.utility.DatabaseException;
import com.gcu.conversationservice.utility.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConversationBusinessService implements ConversationBusinessInterface<ConversationModel> {

    //TODO remove hardcoded address once service discovery is implemented
    private final String userServiceAddress = "127.0.0.1:8080/userservice";
    private DataAccessInterface<ConversationEntity> conversationService;

    @Autowired
    public void setConversationDataService(DataAccessInterface<ConversationEntity> conversationService){
        this.conversationService = conversationService;
    }

    @Override
    public ArrayList<ConversationModel> findAll(){
        List<ConversationEntity> results = conversationService.findAll();

        if(results.size() != 0){
            ArrayList<ConversationModel> returnVal = new ArrayList<>();

            for(ConversationEntity conversation : results){
                returnVal.add(new ConversationModel(conversation));
            }

            return returnVal;
        } else {
            throw new ItemNotFoundException();
        }
    }

    @Override
    public ConversationModel findByID(ConversationModel conversation) {
        //Try and query the database for a conversation model using its ID to search
        Optional<ConversationEntity> result = conversationService.findByID(conversation.toEntity());

        //Check if a conversation was found
        if(result.isPresent()){
            //Return the complete ConversationModel retrieved from the database if a record was successfully found
            return new ConversationModel(result.get());
        } else {
            //Throw a new exception if an error has occured
            throw new ItemNotFoundException();
        }
    }

    @Override
    public ConversationModel create(ConversationModel conversation) {
        //Check if the IDs for the users that will be members of the conversation are valid user IDs
        if(checkUserID(conversation.getUser1()) && checkUserID(conversation.getUser2())) {
            //Attempt to create a new conversation record in the database
            return new ConversationModel(conversationService.create(conversation.toEntity()).orElseThrow(DatabaseException::new));
        } else {
            //Throw an item not found exception in the event that one or both of the user IDs are not valid
            throw new ItemNotFoundException();
        }
    }

    @Override
    public boolean leave(ConversationModel conversation) {
        //Try and delete a conversation record from the database
        return conversationService.delete(conversation.toEntity());
    }

    private boolean checkUserID(String userID){
        //Create a new RestTemplate to check user IDs with the user service
        RestTemplate restTemplate = new RestTemplate();

        //Attempt to check the user ID with the user service
        try {
            //Create a new UserModel and set the ID to the ID that needs to be checked
            UserModel user = new UserModel();
            user.setID(userID);
            //Sent a REST request to the user service to check the user's ID and return whether or not the result is OK
            return restTemplate.exchange("http://"+userServiceAddress+"/getUser", HttpMethod.POST, new HttpEntity<>(user), UserModel.class).getStatusCode() == HttpStatus.OK;
        } catch (Exception e){
            //Throw a new exception in the event of an error
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
