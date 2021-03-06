package com.gcu.userservice.business

import com.gcu.userservice.data.DataAccessInterface
import com.gcu.userservice.data.entity.UserEntity
import com.gcu.userservice.model.UserModel
import com.gcu.userservice.utility.DuplicateUserException
import com.gcu.userservice.utility.ItemNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("userBusinessService")
class UserBusinessService(@Autowired private val dataService: DataAccessInterface<UserEntity>): UserBusinessInterface<UserModel> {

    override fun register(t: UserModel): Boolean {
        dataService.findByString(t.credentials.username)?.let {
            throw DuplicateUserException()
        }

        return dataService.create(t.toEntity())
    }

    override fun authenticate(t: UserModel): UserModel = dataService.findBy(t.toEntity())?.let { UserModel(it) } ?: throw ItemNotFoundException()


    override fun findUserByID(id: String): UserModel = dataService.findByID(id)?.let { UserModel(it) } ?: throw ItemNotFoundException()

    override fun findAll(): List<UserModel> {
        val users = ArrayList<UserModel>()

        dataService.findAll().forEach {
            users.add(UserModel(it))
        }

        return if (users.isNotEmpty()) {
            users
        } else {
            throw ItemNotFoundException()
        }
    }
}