package com.gcu.messageservice.model

import com.gcu.messageservice.data.entity.MessageEntity

data class MessageModel(
    var ID: String,
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
    constructor(message: MessageEntity) : this(
        message.id,
        message.message,
        message.timestamp,
        message.userID,
        message.conversationID
    )

    fun toEntity(): MessageEntity = MessageEntity(ID, message, timestamp, userID, conversationID)
}
