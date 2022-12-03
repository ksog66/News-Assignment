package com.example.newsassignment.data.remote.dto


import com.example.newsassignment.domain.model.Article
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleDTO(
    @Json(name = "author")
    val author: String,
    @Json(name = "content")
    val content: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "publishedAt")
    val publishedAt: String,
    @Json(name = "source")
    val source: SourceDTO,
    @Json(name = "title")
    val title: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "urlToImage")
    val urlToImage: String
)

fun ArticleDTO.toArticle() : Article {
    return Article(
        author = author,
        title = title,
        description = description,
        coverImageUrl = urlToImage,
        newsUrl = url
    )
}