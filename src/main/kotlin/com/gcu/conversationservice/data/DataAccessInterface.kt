package com.gcu.conversationservice.data

interface DataAccessInterface<T> {

    fun findAll(): List<T>
    fun findByID(id: String): T?
    fun create(t: T): T?
    fun delete(t: T): Boolean
}