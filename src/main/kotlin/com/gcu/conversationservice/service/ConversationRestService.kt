package com.gcu.conversationservice.service

import com.gcu.conversationservice.business.ConversationBusinessInterface
import com.gcu.conversationservice.model.ConversationModel
import com.gcu.conversationservice.utility.ItemNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception

@RestController
@RequestMapping
class ConversationRestService(@Autowired private val conversationService: ConversationBusinessInterface<ConversationModel>) {

    @GetMapping(path = ["/"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllConversations(): ResponseEntity<*>? {
        return try {
            ResponseEntity(conversationService.findAll(), HttpStatus.OK)
        } catch (e: ItemNotFoundException) {
            ResponseEntity<Any>(HttpStatus.NO_CONTENT)
        } catch (e: Exception) {
            ResponseEntity<Any>(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping(path = ["/getConversation"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getConversation(@RequestBody conversation: ConversationModel): ResponseEntity<*>? {
        return try {
            //Try and find a conversation by it's ID and return the complete ConversationModel along with HTTP Status 200 if successful
            ResponseEntity(conversationService.findByID(conversation.ID), HttpStatus.OK)
        } catch (e: ItemNotFoundException) {
            //Return a HTTP status code of 204 if no results for the ID were found
            ResponseEntity<Any>(HttpStatus.NO_CONTENT)
        } catch (e: Exception) {
            //Return a HTTP status code of 500 if an error occurred
            ResponseEntity<Any>(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping(path = ["/create"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody conversation: ConversationModel): ResponseEntity<*>? {
        return try {
            //Try and create a new conversation entry in the database and return the complete ConversationModel along with HTTP Status 200 if successful
            ResponseEntity(conversationService.create(conversation), HttpStatus.OK)
        } catch (e: ItemNotFoundException) {
            //Return a HTTP status code of 406 if the data entered is not valid
            ResponseEntity<Any>(HttpStatus.NOT_ACCEPTABLE)
        } catch (e: Exception) {
            //Return a HTTP status code of 500 if an error occurred
            ResponseEntity<Any>(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping(path = ["/leave"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun leave(@RequestBody conversation: ConversationModel): ResponseEntity<*>? {
        return try {
            //Try and delete a conversation record from the database and return a boolean of the result as well as a HTTP status code of 200
            ResponseEntity<Any?>(conversationService.delete(conversation), HttpStatus.OK)
        } catch (e: Exception) {
            //Return a HTTP status code of 500 if an error occured
            ResponseEntity<Any>(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}