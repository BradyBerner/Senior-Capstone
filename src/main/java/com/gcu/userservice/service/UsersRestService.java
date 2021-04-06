package com.gcu.userservice.service;

import com.gcu.userservice.business.UserBusinessInterface;
import com.gcu.userservice.model.CredentialsModel;
import com.gcu.userservice.model.UserModel;
import com.gcu.userservice.utility.DuplicateUserException;
import com.gcu.userservice.utility.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/userservice")
public class UsersRestService {

    private UserBusinessInterface<UserModel> userService;

    @Autowired
    public void setUserBusinessService(UserBusinessInterface<UserModel> userService){
        this.userService = userService;
    }

    @GetMapping(path = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllUsers(){
        try{
            return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
        } catch (ItemNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/getUser", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getUser(@RequestBody UserModel user){
        try{
            //Try and get a user's UserModel based on the provided ID then return it within the response entity alongside an HTTP Status of 200
            return new ResponseEntity<>(userService.findUserByID(user.getID()), HttpStatus.OK);
        } catch (ItemNotFoundException e){
            //Return HTTP Status code of 204 when no user was found with the provided ID
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            //Return HTTP Status code 500 when any unexpected error has occurred within the application
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PostMapping(path = "/login", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> login(@RequestBody CredentialsModel credentials){
        try{
            //Try and verify a user's credentials and return the user's complete UserModel along with HTTP Status 200 if successful
            return new ResponseEntity<>(userService.authenticate(new UserModel("", "", credentials.getUsername(), credentials.getPassword())), HttpStatus.OK);
        } catch (ItemNotFoundException e){
            //Return HTTP Status of 204 if no user was found with the credentials entered
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            //Return HTTP Status of 500 when any unexpected error has occurred within the application
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PostMapping(path = "/register", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> register(@Valid @RequestBody UserModel user){
        try {
            //Try and register a new user with the information contained in the UserModel passed in and return the user's user model with their new ID and an HTTP Status of 200 if successful
            return new ResponseEntity<>(userService.register(user), HttpStatus.OK);
        } catch (DuplicateUserException e){
            //Return HTTP Status code of 406 if the UserModel passed to this method is not valid for any reason
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e){
            //Return HTTP Status of 500 when any unexpected error has occurred within the application
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
