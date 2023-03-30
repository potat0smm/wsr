package com.example.wsr.Api.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wsr.Api.RetrofitClient
import com.example.wsr.Api.SingInResponse
import kotlinx.coroutines.launch

class SignInViewModel:ViewModel() {
    private val apiService = RetrofitClient.apiService
    private val _signInResponse = MutableLiveData<SingInResponse>()
    val signInResponse: LiveData<SingInResponse> = _signInResponse

    fun signIn(email: String, code:String,token:String){
        viewModelScope.launch {
            val response = apiService.signIn(email,code,token)
            _signInResponse.postValue(response.body())
        }
    }
}