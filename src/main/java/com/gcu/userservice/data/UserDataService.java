package com.gcu.userservice.data;

import com.gcu.userservice.data.entity.UserEntity;
import com.gcu.userservice.data.repository.UsersRepository;
import com.gcu.userservice.utility.DatabaseException;
import com.gcu.userservice.utility.NotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

public class UserDataService implements DataAccessInterface<UserEntity> {

    @Autowired
    private UsersRepository usersRepository;

    public List<UserEntity> findAll(){
        try{
            //Try and get all users from the database
            return usersRepository.findAll();
        } catch (Exception e){
            //Throw a database exception in the event of an error
            throw new DatabaseException();
        }
    }

    @Override
    public List<UserEntity> findAllWithID(String id) {
        throw new NotSupportedException();
    }

    @Override
    public Optional<UserEntity> findByID(String id) {
        try{
            //Try and find user in database with specified ID
            return usersRepository.findById(id);
        } catch (Exception e){
            //Throw a database exception in the event of an error
            throw new DatabaseException();
        }
    }

    @Override
    public UserEntity findBy(UserEntity userEntity) {
        try{
            //Try and find a user by their username and password to authenticate their login credentials
            return usersRepository.getUserEntityByUsernameAndPassword(userEntity.getUsername(), userEntity.getPassword());
        } catch (Exception e){
            //Throw a database exception in the event of an error
            throw new DatabaseException();
        }
    }

    @Override
    public UserEntity findByString(String search) {
        try {
            //Try and find a user by their username or email for the purpose of letting another user start a conversation with them
            return usersRepository.getUserEntityByUsernameOrEmail(search, search);
        } catch (Exception e){
            //Throw a database exception in the event of an error
            throw new DatabaseException();
        }
    }

    @Override
    public boolean create(UserEntity userEntity) {
        try {
            //Try and create a new user entry in the database and then evaluate success by checking if the database assigned the user an ID
            return usersRepository.insert(userEntity).getId().equals("");
        } catch (Exception e){
            //Throw a database exception in the event of an error
            throw new DatabaseException();
        }
    }

    @Override
    public int update(UserEntity userEntity) {
        throw new NotSupportedException();
    }

    @Override
    public int delete(UserEntity userEntity) {
        throw new NotSupportedException();
    }
}
