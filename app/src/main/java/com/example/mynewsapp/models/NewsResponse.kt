package com.example.mynewsapp.models

data class NewsResponse(
    var articles: ArrayList<Article>,
    var status: String,
    var totalResults: Int
)