package com.example.wsr.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BaseUrl {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://medic.madskill.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: SimpleApi by lazy{
        retrofit.create(SimpleApi::class.java)
    }


}