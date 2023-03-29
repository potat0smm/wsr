package com.example.wsr.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BaseUrl {


    private val retrofit = Retrofit.Builder()
            .baseUrl("https://medic.madskill.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val apiService = retrofit.create(SimpleApi::class.java)
    }
