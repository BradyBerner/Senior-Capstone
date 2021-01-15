package com.gcu.MessageService.data.repository;

import com.gcu.MessageService.data.entity.MessageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface MessageRepository extends MongoRepository<MessageEntity, String> {

    //Get all the messages with the same conversation id
    List<MessageEntity> findAllByConversationID(String conversation_id);

    //Create a new message record in the database
    @Override
    <S extends MessageEntity> S insert(S s);

    //Update an existing message record in the database
    @Override
    <S extends MessageEntity> S save(S s);

    //Delete a message record from the database
    @Override
    void deleteById(String s);
}
