package com.example.wsr.Api

import com.google.gson.annotations.SerializedName

data class CodeResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("error")
    val error: String
)
data class Email(
    @SerializedName("email")
    val email: String
)
