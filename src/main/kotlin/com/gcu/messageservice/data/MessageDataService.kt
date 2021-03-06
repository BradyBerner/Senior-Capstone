package com.gcu.messageservice.data

import com.gcu.messageservice.data.entity.MessageEntity
import com.gcu.messageservice.data.repository.MessageRepository
import com.gcu.messageservice.utility.DatabaseException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service("messageDataService")
class MessageDataService(@Autowired private val messageRepository: MessageRepository): DataAccessInterface<MessageEntity> {

    override fun getAll(): List<MessageEntity> {
        return try {
            messageRepository.findAll()
        } catch (e: Exception) {
            throw DatabaseException()
        }
    }

    override fun findAllWithID(id: String): List<MessageEntity> {
        return try {
            messageRepository.findAllByConversationID(id)
        } catch (e: Exception) {
            throw DatabaseException()
        }
    }

    override fun create(t: MessageEntity): MessageEntity? {
        return try {
            messageRepository.insert(t)
        } catch (e: Exception) {
            throw DatabaseException()
        }
    }

    override fun update(t: MessageEntity): MessageEntity? {
        return try {
            messageRepository.save(t)
        } catch (e: Exception) {
            throw DatabaseException()
        }
    }

    override fun delete(t: MessageEntity): Boolean {
        return try {
            messageRepository.deleteById(t.id)
            true
        } catch (e: IllegalArgumentException) {
            false
        } catch (e: Exception) {
            throw DatabaseException()
        }
    }
}