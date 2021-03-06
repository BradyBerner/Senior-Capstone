package com.gcu.userservice.business

interface UserBusinessInterface<T> {

    fun register(t: T): Boolean
    fun authenticate(t: T): T
    fun findUserByID(id: String): T
    fun findAll(): List<T>
}