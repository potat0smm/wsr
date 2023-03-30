package com.example.wsr.Api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("api/catalog")
    suspend fun getCatalog(): Response<List<CatalogItem>>

    @GET("api/catalog/{id}")
    suspend fun getItemById(@Path("id")id:Int):CatalogItem
    @GET("api/news")
    suspend fun getNews(): Response<List<NewsItem>>

    @POST ("api/sendCode")
    @Headers("Content-Type: application/json")
    fun sendCode(@Header("email") email: String):Call<CodeResponse>

    @POST("api/signin")
    suspend fun signIn(
        @Header("email") email: String,
        @Header("code") code: String,
        @Header("token") token:String
    ): Response<SingInResponse>

}