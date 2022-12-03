package com.example.newsassignment.data.repository

import android.util.Log
import com.example.newsassignment.common.DispatcherProvider
import com.example.newsassignment.common.Resource
import com.example.newsassignment.data.remote.NewsApi
import com.example.newsassignment.data.remote.dto.ArticleDTO
import com.example.newsassignment.data.remote.dto.NewsResponse
import com.example.newsassignment.data.remote.dto.toArticle
import com.example.newsassignment.domain.model.Article
import com.example.newsassignment.domain.repository.NewsRepository
import com.example.newsassignment.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val TAG = "NewsRepositoryImpl"

class NewsRepositoryImpl @Inject constructor(
    private val dispatcher: DispatcherProvider,
    private val newsApi: NewsApi
) : NewsRepository {

    override suspend fun getTopHeadlines(): Flow<Resource<List<Article>>> =
        withContext(dispatcher.io) {
            return@withContext flow {
                try {
                    emit(Resource.Loading())
                    val response = newsApi.getTopHeadlines().articles.map(ArticleDTO::toArticle)
                    emit(Resource.Success(response))
                } catch (e: HttpException) {
                    Log.e(TAG, e.localizedMessage)
                    emit(Resource.Error(Constants.Errors.UNEXPECTED_ERROR_OCCURRED_TRY_AGAIN))
                } catch (e: IOException) {
                    Log.e(TAG, e.localizedMessage )
                    emit(Resource.Error(Constants.Errors.UNEXPECTED_ERROR_OCCURRED_TRY_AGAIN))
                }
            }
        }
}