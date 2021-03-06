package com.gcu.messageservice.data.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "messages")
data class MessageEntity(
    @Id
    var id: String,
    var message: String,
    var timestamp: String,
    var userID: String,
    var conversationID: String
){
    constructor() : this(
        "",
        "",
        "",
        "",
        ""
    )
}
