package com.example.wsr.Api.model

import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {
    var id: Int? = null
    var firstname: String? = null
    var lastname: String? = null
    var middlename: String? = null
    var bith: String? = null
    var pol: String? = null
    var token: String? = null

    fun saveToken(token: String) {
        this.token = token
    }

    fun saveUser(
        id: Int,
        firstname: String,
        lastname: String,
        middlename: String,
        bith: String,
        pol: String
    ) {
        this.bith = bith
        this.id = id
        this.pol = this.pol
        this.firstname = firstname
        this.lastname = lastname
        this.middlename = middlename
    }
}