package com.example.newsassignment.domain.use_case

import com.example.newsassignment.common.Resource
import com.example.newsassignment.data.remote.dto.NewsResponse
import com.example.newsassignment.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class FetchTopHeadlinesUseCase(private val newsRepository: NewsRepository) {

    suspend fun perform(): Flow<Resource<NewsResponse>> {
        return newsRepository.getTopHeadlines()
    }
}