package com.gcu.messageservice.service

import com.gcu.messageservice.business.MessageBusinessInterface
import com.gcu.messageservice.model.ConversationModel
import com.gcu.messageservice.model.MessageModel
import com.gcu.messageservice.utility.ItemNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception
import java.lang.IllegalArgumentException

@RestController
@RequestMapping("/messageservice")
class MessageRestService(@Autowired private val messageService: MessageBusinessInterface<MessageModel, ConversationModel>) {

    @GetMapping(path = ["/"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAll(): ResponseEntity<*>? {
        return try {
            ResponseEntity<Any?>(messageService.getAll(), HttpStatus.OK)
        } catch (e: ItemNotFoundException) {
            ResponseEntity<Any>(HttpStatus.NO_CONTENT)
        } catch (e: Exception) {
            ResponseEntity<Any>(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping(path = ["/view"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun view(@RequestBody conversation: ConversationModel): ResponseEntity<*>? {
        return try {
            //Try and get all of the messages contained within a conversation and return them all along with a HTTP status code of 200 if successful
            ResponseEntity<Any?>(messageService.view(conversation), HttpStatus.OK)
        } catch (e: ItemNotFoundException) {
            //Return a HTTP status code of 204 in the event that there are no messages in the conversation
            ResponseEntity<Any>(HttpStatus.NO_CONTENT)
        } catch (e: Exception) {
            //Return a HTTP status code of 500 in the event that an error occurs
            ResponseEntity<Any>(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping(path = ["/send"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun send(@RequestBody message: MessageModel): ResponseEntity<*>? {
        return try {
            //Try and create a new message record and return the complete MessageModel along with a HTTP status code of 200 if successful
            ResponseEntity<Any?>(messageService.send(message), HttpStatus.OK)
        } catch (e: IllegalArgumentException) {
            //TODO implement data validation
            //Return a HTTP status code of 406 if the message sent is not valid
            ResponseEntity<Any>(HttpStatus.NOT_ACCEPTABLE)
        } catch (e: Exception) {
            //Return a HTTP status code of 500 in the event that an error occurs
            ResponseEntity<Any>(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping(path = ["/edit"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun edit(@RequestBody message: MessageModel): ResponseEntity<*>? {
        return try {
            //Try and update an existing message and return the updated MessageModel and a HTTP status code of 200 if successful
            ResponseEntity<Any?>(messageService.edit(message), HttpStatus.OK)
        } catch (e: IllegalArgumentException) {
            //TODO implement data validation
            //Return a HTTP status code of 406 if the message edited is not valid
            ResponseEntity<Any>(HttpStatus.NOT_ACCEPTABLE)
        } catch (e: Exception) {
            //Return a HTTP status code of 500 in the event that an error occurs
            ResponseEntity<Any>(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping(path = ["/delete"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun delete(@RequestBody message: MessageModel): ResponseEntity<*>? {
        return try {
            //Try and delete a message record from the database and return a boolean and a HTTP status code of 200
            ResponseEntity<Any?>(messageService.delete(message), HttpStatus.OK)
        } catch (e: Exception) {
            //Return a HTTP status code of 500 in the event that an error occurs
            ResponseEntity<Any>(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}