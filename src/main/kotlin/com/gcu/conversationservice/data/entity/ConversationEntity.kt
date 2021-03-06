package com.gcu.conversationservice.data.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "conversations")
data class ConversationEntity(
    @Id
    var id: String,
    var user_1: String,
    var user_2: String
){
    constructor() : this(
        "",
        "",
        ""
    )
}
