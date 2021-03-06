package com.gcu.userservice.model

import javax.validation.constraints.NotBlank

data class CredentialsModel(
    @field:NotBlank
    var username: String,
    @field:NotBlank
    var password: String
){
    constructor() : this(
        "",
        ""
    )
}
