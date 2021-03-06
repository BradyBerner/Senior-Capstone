package com.gcu.conversationservice.business

import com.gcu.conversationservice.data.DataAccessInterface
import com.gcu.conversationservice.data.entity.ConversationEntity
import com.gcu.conversationservice.model.ConversationModel
import com.gcu.conversationservice.model.UserModel
import com.gcu.conversationservice.utility.DatabaseException
import com.gcu.conversationservice.utility.ItemNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service("conversationBusinessService")
class ConversationBusinessService(@Autowired private val dataService: DataAccessInterface<ConversationEntity>): ConversationBusinessInterface<ConversationModel> {

    override fun findAll(): List<ConversationModel> {
        val conversations = ArrayList<ConversationModel>()

        dataService.findAll().forEach {
            conversations.add(ConversationModel(it))
        }

        return if (conversations.isNotEmpty()){
            conversations
        } else {
            throw ItemNotFoundException()
        }
    }

    override fun findByID(id: String): ConversationModel = dataService.findByID(id)?.let { ConversationModel(it) } ?: throw ItemNotFoundException()

    //TODO: implement check user ID before creating conversation
    override fun create(t: ConversationModel): ConversationModel = dataService.create(t.toEntity())?.let { ConversationModel(it) } ?: throw DatabaseException()

    override fun delete(t: ConversationModel): Boolean = dataService.delete(t.toEntity())

    private fun checkUserID(userID: String): Boolean {
        val template = RestTemplate()

        return try {
            val user = UserModel()
            user.ID = userID

            template.exchange<UserModel>(
                "http://127.0.0.1:8080/userservice/getUser", HttpMethod.POST, HttpEntity(user),
                UserModel::class.java
            ).statusCode == HttpStatus.OK
        } catch (e: Exception) {
            throw RuntimeException()
        }
    }
}