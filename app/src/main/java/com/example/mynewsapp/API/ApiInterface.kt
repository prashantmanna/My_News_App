package com.example.mynewsapp.API

import com.example.mynewsapp.API.Constants.Companion.API_KEY
import com.example.mynewsapp.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("country") country: String,
        @Query("page") pageNumber: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponse>

    @GET("everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("page") pageNumber: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponse>
}
