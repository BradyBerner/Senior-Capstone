package com.gcu.conversationservice.model

import com.gcu.conversationservice.data.entity.ConversationEntity

data class ConversationModel(
    var ID: String,
    var user1: String,
    var user2: String
){
    constructor() : this(
        "",
        "",
        ""
    )
    constructor(conversation: ConversationEntity) : this(
        conversation.id,
        conversation.user_1,
        conversation.user_2
    )

    fun toEntity(): ConversationEntity = ConversationEntity(ID, user1, user2)
}
