package com.gcu.userservice.data.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class UserEntity(
    @Id
    var id: String,
    @Indexed(unique = true)
    var email: String,
    var username: String,
    var password: String
){
    constructor() : this(
        "",
        "",
        "",
        ""
    )
}
