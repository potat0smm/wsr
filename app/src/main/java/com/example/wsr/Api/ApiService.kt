package com.example.wsr.Api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.util.stream.IntStream

interface ApiService {

    @GET("api/catalog")
    suspend fun getCatalog(): Response<List<CatalogItem>>

    @GET("api/catalog")
    fun getCategory(@Header("category")category:String):Response<List<CatalogItem>>

    @GET("api/catalog/{id}")
    suspend fun getItemById(@Path("id")id:Int):CatalogItem
    @GET("api/news")
    suspend fun getNews(): Response<List<NewsItem>>

    @POST ("api/sendCode")
    @Headers("Content-Type: application/json")
    fun sendCode(@Header("email") email: String):Call<sendCode>

    @POST("api/signin")
    fun signIn(
        @Header("email") email: String,
        @Header("code") code: String
    ): Call<signIn>

    @POST("api/createProfile")
    @Headers("Content-Type: application/json")
    fun createUser(
        @Header("firstname") firstname:String,
        @Header("lastname") lastname:String,
        @Header("middlename") middlename:String,
        @Header("bith") bith: IntStream,
        @Header("pol") pol:String,
        @Header("id") id: Int
        // @Header("image") image:String
    ):Call<createUser>

    @PUT("api/updateProfile")
    @Headers("Content-Type: application/json")
    fun updateUser(
        @Header("firstname") firstname: String,
        @Header("firstname") lastname: String,
        @Header("firstname") middlename: String,
        @Header("firstname") bith: String,
        @Header("pol") pol:String
    ):Call<UpdateUser>

}