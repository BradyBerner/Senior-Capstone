package com.gcu.messageservice.model

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
}
