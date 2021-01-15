package com.gcu.conversationservice.data.repository;

import com.gcu.conversationservice.data.entity.ConversationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConversationRepository extends MongoRepository<ConversationEntity, String> {

    //Create a new record in the database
    <S extends ConversationEntity> S insert(S conversation);
}
