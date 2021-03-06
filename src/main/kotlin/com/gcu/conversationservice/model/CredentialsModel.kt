package com.gcu.conversationservice.model

data class CredentialsModel(
    var username: String,
    var password: String
){
    constructor() : this(
        "",
        ""
    )
}
