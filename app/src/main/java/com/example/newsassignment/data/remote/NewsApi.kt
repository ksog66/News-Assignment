package com.example.newsassignment.data.remote

import com.example.newsassignment.data.remote.dto.NewsResponse
import com.example.newsassignment.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET(Constants.TOP_HEADLINES_ENDPOINT)
    suspend fun getTopHeadlines(
        @Query("sources") source: String = Constants.NEWS_SOURCE,
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ) : NewsResponse
}