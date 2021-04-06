package com.gcu.userservice.business;

import com.gcu.userservice.data.DataAccessInterface;
import com.gcu.userservice.data.entity.UserEntity;
import com.gcu.userservice.model.UserModel;
import com.gcu.userservice.utility.DuplicateUserException;
import com.gcu.userservice.utility.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("userBusinessService")
public class UserBusinessService implements UserBusinessInterface<UserModel> {

    private DataAccessInterface<UserEntity> userService;

    @Autowired
    public void setUserDataService(DataAccessInterface<UserEntity> userService){
        this.userService = userService;
    }

    @Override
    public boolean register(UserModel user) {
        //Check that there's no user with that username already in the database
        if(userService.findByString(user.getCredentials().getUsername()).isPresent()) {
            throw new DuplicateUserException();
        }

        //Attempt to add entry to database
        return userService.create(user.toEntity());
    }

    @Override
    public UserModel authenticate(UserModel user) {
        //Try and get the UserModel for a user if they used the correct login credentials
        Optional<UserEntity> result = userService.findBy(user.toEntity());

        //Check if there was a user with matching credentials
        if(result.isPresent()){
            //Return the user's complete UserModel if successfully logged in
            return new UserModel(result.get());
        } else {
            //Throw exception if there was no user with a matching username and password
            throw new ItemNotFoundException();
        }
    }

    @Override
    public UserModel findUserByID(String id) {
        //Try and find a user by their ID
        Optional<UserEntity> result = userService.findByID(id);

        //Check if a user was found
        if(result.isPresent()){
            //return the user's complete UserModel if they were found
            return new UserModel(result.get());
        } else {
            //Throw new exception if there was no user with the searched ID
            throw new ItemNotFoundException();
        }
    }

    @Override
    public List<UserModel> findAll() {
        ArrayList<UserModel> returnVal = new ArrayList<>();
        List<UserEntity> results = userService.findAll();

        if(!results.isEmpty()){
            for(UserEntity user : results){
                returnVal.add(new UserModel(user));
            }

            return returnVal;
        } else {
            throw new ItemNotFoundException();
        }
    }
}
