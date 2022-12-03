package com.example.newsassignment.utils

import com.example.newsassignment.BuildConfig

object Constants {

    const val API_KEY = BuildConfig.API_KEY

    const val BASE_URL = "https://newsapi.org/v2/"
    const val TOP_HEADLINES_ENDPOINT = "top-headlines"

    const val NEWS_SOURCE = "techcrunch"

    object Errors {
        const val UNEXPECTED_ERROR_OCCURRED_TRY_AGAIN = "Unexpected error occurred! Please Try Again"
    }
}