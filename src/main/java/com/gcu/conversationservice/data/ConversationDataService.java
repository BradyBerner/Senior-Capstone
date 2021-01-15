package com.gcu.conversationservice.data;

import com.gcu.conversationservice.data.entity.ConversationEntity;
import com.gcu.conversationservice.data.repository.ConversationRepository;
import com.gcu.conversationservice.utility.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

public class ConversationDataService implements DataAccessInterface<ConversationEntity>{

    @Autowired
    private ConversationRepository conversationRepository;

    public Optional<ConversationEntity> findByID(ConversationEntity conversationEntity){
        try {
            //Try and find a conversation in the database with a matching ID
            return conversationRepository.findById(conversationEntity.getId());
        } catch (Exception e){
            //Throw a database exception if an error occurs
            throw new DatabaseException();
        }
    }

    public ConversationEntity create(ConversationEntity conversationEntity) {
        try{
            //Try create a new conversation record in the database
            return conversationRepository.insert(conversationEntity);
        } catch (Exception e){
            //Throw a database exception if an error occurs
            throw new DatabaseException();
        }
    }

    public boolean delete(ConversationEntity conversationEntity) {
        try{
            //Try and remove a conversation from the database based on its ID
            conversationRepository.deleteById(conversationEntity.getId());
            //Return true if no error occurred and therefore the action was successful
            return true;
        } catch (IllegalArgumentException e){
            //Return false if the repository threw an IllegalArgumentException meaning that the ID entered was invalid
            return false;
        } catch (Exception e){
            //Throw a database exception if an error occurs
            throw new DatabaseException();
        }
    }
}
