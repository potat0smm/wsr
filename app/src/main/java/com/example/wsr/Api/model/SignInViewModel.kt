package com.example.wsr.Api.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wsr.Api.RetrofitClient
import com.example.wsr.Api.SingInResponse
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {
    var token: String? = null

    fun saveToken(token: String) {
        this.token = token
    }
}