package com.example.wsr.Api

data class SingInResponse(
    val email:String,
    val code:String,
    val token:String?
)
