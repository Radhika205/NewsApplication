package com.example.newsapplication.model

import com.example.newsapplication.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)