package com.example.wsr.Api

import com.bumptech.glide.load.engine.Resource
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface SimpleApi {

    @GET("api/catalog")
    suspend fun getCatalog(): Response<List<CatalogItem>>

    @GET("api/catalog/{id}")
    suspend fun getItemById(@Path("id")id:Int):CatalogItem
    @GET("api/news")
    suspend fun getNews(): Response<List<NewsItem>>

    @FormUrlEncoded
    @POST ("api/sendCode")
    suspend fun sendCode(@Field("email") email: String): Response<Void>

    @POST("api/signin")
    suspend fun signIn(
        @Header("email") email: String,
        @Header("code") code: String
    ): Response<SingInResponse>

}