package com.gcu.MessageService.data;

import com.gcu.MessageService.data.entity.MessageEntity;
import com.gcu.MessageService.data.repository.MessageRepository;
import com.gcu.MessageService.utility.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

public class MessageDataService implements DataAccessInterface<MessageEntity> {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<MessageEntity> getAll(){
        try{
            return messageRepository.findAll();
        } catch (Exception e){
            throw new DatabaseException();
        }
    }

    @Override
    public List<MessageEntity> findAll(String id) {
        try {
            //Try and find all the messages with the same conversation ID
            return messageRepository.findAllByConversationID(id);
        } catch (Exception e){
            //Throw a database exception in the event an error occurs
            throw new DatabaseException();
        }
    }

    @Override
    public Optional<MessageEntity> create(MessageEntity messageEntity) {
        try {
            //Try and create a new message entry in the database
            return Optional.of(messageRepository.insert(messageEntity));
        } catch (Exception e){
            //Throw a database exception in the event an error occurs
            throw new DatabaseException();
        }
    }

    @Override
    public Optional<MessageEntity> update(MessageEntity messageEntity) {
        try {
            //Try and update an existing database entry
            return Optional.of(messageRepository.save(messageEntity));
        } catch (Exception e){
            //Throw a database exception in the event an error occurs
            throw new DatabaseException();
        }
    }

    @Override
    public boolean delete(MessageEntity messageEntity) {
        try{
            //Try and delete the message entry in the database
            messageRepository.deleteById(messageEntity.getId());
            //Return true in the event that the message was successfully deleted
            return true;
        } catch (IllegalArgumentException e){
            //Return false in the event that the repository threw an IllegalArgumentException meaning that the ID was not valid
            return false;
        } catch (Exception e){
            //Throw a database exception in the event an error occurs
            throw new DatabaseException();
        }
    }
}
