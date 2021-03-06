package com.gcu.messageservice.data.repository

import com.gcu.messageservice.data.entity.MessageEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface MessageRepository: MongoRepository<MessageEntity, String> {

    fun findAllByConversationID(conversationID: String): List<MessageEntity>
}