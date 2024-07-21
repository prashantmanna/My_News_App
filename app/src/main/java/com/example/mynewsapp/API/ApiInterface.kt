package com.example.mynewsapp.API

import androidx.annotation.Size
import com.example.mynewsapp.API.Constants.Companion.API_KEY
import com.example.mynewsapp.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("country") country: String,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponse>

    @GET("top-headlines")
    suspend fun getCategory(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponse>
}
