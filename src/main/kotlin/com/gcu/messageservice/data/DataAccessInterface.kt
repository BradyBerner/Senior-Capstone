package com.gcu.messageservice.data

interface DataAccessInterface<T> {

    fun getAll(): List<T>
    fun findAllWithID(id: String): List<T>
    fun create(t: T): T?
    fun update(t: T): T?
    fun delete(t: T): Boolean
}