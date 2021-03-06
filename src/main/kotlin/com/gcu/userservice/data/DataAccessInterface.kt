package com.gcu.userservice.data

interface DataAccessInterface<T> {

    fun findAll(): List<T>
    fun findAllWithID(id: String): List<T>
    fun findByID(id: String): T?
    fun findBy(t: T): T?
    fun findByString(search: String): T?
    fun create(t: T): Boolean
    fun update(t: T): Boolean
    fun delete(t: T): Boolean
}