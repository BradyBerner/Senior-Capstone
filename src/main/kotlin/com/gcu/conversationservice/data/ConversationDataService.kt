package com.gcu.conversationservice.data

import com.gcu.conversationservice.data.entity.ConversationEntity
import com.gcu.conversationservice.data.repository.ConversationRepository
import com.gcu.conversationservice.utility.DatabaseException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("conversationDataService")
class ConversationDataService(@Autowired private val conversationRepository: ConversationRepository): DataAccessInterface<ConversationEntity> {

    override fun findAll(): List<ConversationEntity> {
        return try {
            conversationRepository.findAll()
        } catch (e: Exception) {
            throw DatabaseException()
        }
    }

    override fun findByID(id: String): ConversationEntity? {
        return try {
            conversationRepository.findById(id).orElseGet(null)
        } catch (e: Exception) {
            throw DatabaseException()
        }
    }

    override fun create(t: ConversationEntity): ConversationEntity? {
        return try {
            conversationRepository.insert(t)
        } catch (e: java.lang.Exception) {
            throw DatabaseException()
        }
    }

    override fun delete(t: ConversationEntity): Boolean {
        return try {
            conversationRepository.deleteById(t.id)
            true
        } catch (e: IllegalArgumentException) {
            false
        } catch (e: Exception) {
            throw DatabaseException()
        }
    }
}