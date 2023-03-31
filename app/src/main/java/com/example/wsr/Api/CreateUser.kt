package com.example.wsr.Api

data class CreateUser(
    val id: Int,
    val bith: String,
    val firstname: String,
    val image: String,
    val lastname: String,
    val middlename: String,
    val pol: String
)