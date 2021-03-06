package com.gcu.messageservice.business

import com.gcu.messageservice.data.DataAccessInterface
import com.gcu.messageservice.data.entity.MessageEntity
import com.gcu.messageservice.model.ConversationModel
import com.gcu.messageservice.model.MessageModel
import com.gcu.messageservice.utility.DatabaseException
import com.gcu.messageservice.utility.ItemNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.lang.Exception

@Service("messageBusinessService")
class MessageBusinessService(@Autowired private val dataService: DataAccessInterface<MessageEntity>): MessageBusinessInterface<MessageModel, ConversationModel> {

    override fun getAll(): List<MessageModel> {
        val messages = ArrayList<MessageModel>()

        dataService.getAll().forEach {
            messages.add(MessageModel(it))
        }

        return if (messages.isNotEmpty())
            messages
        else
            throw ItemNotFoundException()
    }

    override fun view(s: ConversationModel): List<MessageModel> {
        val messages = ArrayList<MessageModel>()

        dataService.findAllWithID(s.ID).forEach {
            messages.add(MessageModel(it))
        }

        return if (messages.isNotEmpty())
            messages
        else
            throw ItemNotFoundException()
    }

    override fun send(t: MessageModel): MessageModel {
        return if (checkConversationID(t.conversationID))
            dataService.create(t.toEntity())?.let { MessageModel(it) } ?: throw DatabaseException()
        else
            throw ItemNotFoundException()
    }

    override fun edit(t: MessageModel): MessageModel = MessageModel(dataService.update(t.toEntity()) ?: throw DatabaseException())

    override fun delete(t: MessageModel): Boolean = dataService.delete(t.toEntity())

    private fun checkConversationID(conversationID: String): Boolean {
        val restTemplate = RestTemplate()

        return try {
            val conversation = ConversationModel()
            conversation.ID = conversationID

            restTemplate.exchange(
                "http://127.0.0.1:8081/conversationservice/getConversation", HttpMethod.POST, HttpEntity(conversation),
                ConversationModel::class.java
            ).statusCode === HttpStatus.OK
        } catch (e: Exception) {
            throw RuntimeException()
        }
    }
}