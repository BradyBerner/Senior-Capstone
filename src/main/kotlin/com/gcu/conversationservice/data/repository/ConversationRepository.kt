package com.gcu.conversationservice.data.repository

import com.gcu.conversationservice.data.entity.ConversationEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface ConversationRepository: MongoRepository<ConversationEntity, String>