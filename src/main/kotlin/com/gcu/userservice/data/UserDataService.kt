package com.gcu.userservice.data

import com.gcu.userservice.data.entity.UserEntity
import com.gcu.userservice.data.repository.UsersRepository
import com.gcu.userservice.utility.DatabaseException
import com.gcu.userservice.utility.ItemNotFoundException
import com.gcu.userservice.utility.NotSupportedException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("userDataService")
class UserDataService(@Autowired private val userRepository: UsersRepository): DataAccessInterface<UserEntity> {


    override fun findAll(): List<UserEntity> {
        return try {
            userRepository.findAll()
        } catch (e: Exception) {
            throw DatabaseException()
        }
    }

    override fun findAllWithID(id: String): List<UserEntity> {
        throw ItemNotFoundException()
    }

    override fun findByID(id: String): UserEntity? {
        return try {
            userRepository.findById(id).orElseGet(null)
        } catch (e :Exception) {
            throw DatabaseException()
        }
    }

    override fun findBy(t: UserEntity): UserEntity? {
        return try {
            userRepository.getUserEntitiesByUsernameAndPassword(t.username, t.password)
        } catch (e: Exception) {
            throw DatabaseException()
        }
    }

    override fun findByString(search: String): UserEntity? {
        return try {
            userRepository.getUserEntitiesByUsernameOrEmail(search, search)
        } catch (e: Exception) {
            throw DatabaseException()
        }
    }

    override fun create(t: UserEntity): Boolean {
        return try {
            userRepository.insert(t)
            true
        } catch (e: Exception) {
            throw DatabaseException()
        }
    }

    override fun update(t: UserEntity): Boolean {
        throw NotSupportedException()
    }

    override fun delete(t: UserEntity): Boolean {
        throw NotSupportedException()
    }
}