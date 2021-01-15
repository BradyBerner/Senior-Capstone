package com.gcu.userservice.data.repository;

import com.gcu.userservice.data.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UsersRepository extends MongoRepository<UserEntity, String> {

    //Find a user using their ID
    Optional<UserEntity> findById(String s);
    //Find a user using their username and password to authenticate their login
    UserEntity getUserEntityByUsernameAndPassword(String username, String password);
    //Find a user by their username or email for the purpose of users starting conversations
    UserEntity getUserEntityByUsernameOrEmail(String username, String email);
    //Insert a new user entry to the database
    @Override
    <S extends UserEntity> S insert(S user);
}
