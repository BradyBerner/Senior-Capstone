package com.gcu.messageservice.business

interface MessageBusinessInterface<T, S> {

    fun getAll(): List<T>
    fun view(s: S): List<T>
    fun send(t: T): T
    fun edit(t: T): T
    fun delete(t: T): Boolean
}