package com.furkansemizoglu.newsapp2.model

data class NewsModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)