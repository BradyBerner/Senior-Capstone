package com.gcu.userservice.model

import com.gcu.userservice.data.entity.UserEntity
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@Document(collection = "users")
data class UserModel(
    var ID: String,
    @field:NotBlank
    var email: String,
    @field:Valid
    var credentials: CredentialsModel
){
    constructor() : this(
        "",
        "",
        CredentialsModel("", "")
    )
    constructor(user: UserEntity) : this(
        user.id,
        user.email,
        CredentialsModel(user.username, user.password)
    )

    fun toEntity(): UserEntity = UserEntity(ID, email, credentials.username, credentials.password)
}
