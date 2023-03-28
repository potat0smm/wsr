package com.example.wsr.Api

import com.bumptech.glide.load.engine.Resource
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SimpleApi {

    @GET("api/catalog")
    suspend fun getCatalog(): Response<List<Catalog>>

    @GET("api/catalog/{id}")
    suspend fun getItemById(@Path("id")id:Int):Catalog
    @GET("api/news")
    suspend fun getNews(): Response<List<News>>
}