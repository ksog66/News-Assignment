package com.example.newsassignment.domain.model

data class Article(
    val author: String,
    val title: String,
    val description: String,
    val coverImageUrl: String,
    val newsUrl: String
)
