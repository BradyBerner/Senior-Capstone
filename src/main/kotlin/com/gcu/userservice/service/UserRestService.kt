package com.gcu.userservice.service

import com.gcu.userservice.business.UserBusinessInterface
import com.gcu.userservice.model.CredentialsModel
import com.gcu.userservice.model.UserModel
import com.gcu.userservice.utility.DuplicateUserException
import com.gcu.userservice.utility.ItemNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception
import javax.validation.Valid

@RestController
@RequestMapping("/userservice")
class UserRestService(@Autowired private val userService: UserBusinessInterface<UserModel>) {

    @GetMapping(path = ["/"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllUsers(): ResponseEntity<*>? {
        return try {
            ResponseEntity(userService.findAll(), HttpStatus.OK)
        } catch (e: ItemNotFoundException) {
            ResponseEntity<Any>(HttpStatus.NO_CONTENT)
        } catch (e: Exception) {
            ResponseEntity<Any>(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping(path = ["/getUser"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUser(@RequestBody user: UserModel): ResponseEntity<*>? {
        return try {
            //Try and get a user's UserModel based on the provided ID then return it within the response entity alongside an HTTP Status of 200
            ResponseEntity(userService.findUserByID(user.ID), HttpStatus.OK)
        } catch (e: ItemNotFoundException) {
            //Return HTTP Status code of 204 when no user was found with the provided ID
            ResponseEntity<Any>(HttpStatus.NO_CONTENT)
        } catch (e: Exception) {
            //Return HTTP Status code 500 when any unexpected error has occurred within the application
            ResponseEntity<Any>(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping(path = ["/login"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun login(@Valid @RequestBody credentials: CredentialsModel): ResponseEntity<*>? {
        return try {
            //Try and verify a user's credentials and return the user's complete UserModel along with HTTP Status 200 if successful
            ResponseEntity(
                userService.authenticate(UserModel("", "", credentials)),
                HttpStatus.OK
            )
        } catch (e: ItemNotFoundException) {
            //Return HTTP Status of 204 if no user was found with the credentials entered
            ResponseEntity<Any>(HttpStatus.NO_CONTENT)
        } catch (e: Exception) {
            //Return HTTP Status of 500 when any unexpected error has occurred within the application
            ResponseEntity<Any>(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping(path = ["/register"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun register(@Valid @RequestBody user: UserModel?): ResponseEntity<*>? {
        return try {
            //Try and register a new user with the information contained in the UserModel passed in and return the user's user model with their new ID and an HTTP Status of 200 if successful
            ResponseEntity(userService.register(user!!), HttpStatus.OK)
        } catch (e: DuplicateUserException) {
            //Return HTTP Status code of 406 if the UserModel passed to this method is not valid for any reason
            ResponseEntity<Any>(HttpStatus.NOT_ACCEPTABLE)
        } catch (e: Exception) {
            //Return HTTP Status of 500 when any unexpected error has occurred within the application
            ResponseEntity<Any>(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}