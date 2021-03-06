package com.gcu.userservice.data.repository

import com.gcu.userservice.data.entity.UserEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface UsersRepository: MongoRepository<UserEntity, String> {

    fun getUserEntitiesByUsernameAndPassword(username: String, password: String): UserEntity?
    fun getUserEntitiesByUsernameOrEmail(username: String, email: String): UserEntity?
}