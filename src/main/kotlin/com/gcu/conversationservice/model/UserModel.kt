package com.gcu.conversationservice.model

data class UserModel(
    var ID: String,
    var email: String,
    var credentials: CredentialsModel
){
    constructor() : this(
        "",
        "",
        CredentialsModel("", "")
    )
}
