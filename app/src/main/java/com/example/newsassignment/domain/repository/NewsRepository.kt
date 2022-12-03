package com.example.newsassignment.domain.repository

import com.example.newsassignment.common.Resource
import com.example.newsassignment.data.remote.dto.NewsResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getTopHeadlines() : Flow<Resource<NewsResponse>>
}