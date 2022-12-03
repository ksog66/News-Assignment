package com.example.newsassignment.di

import com.example.newsassignment.common.DispatcherProvider
import com.example.newsassignment.data.remote.NewsApi
import com.example.newsassignment.domain.repository.NewsRepository
import com.example.newsassignment.domain.use_case.FetchTopHeadlinesUseCase
import com.example.newsassignment.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideDispatcherProvider(): DispatcherProvider {
        return object: DispatcherProvider {
            override val main: CoroutineDispatcher
                get() = Dispatchers.Main
            override val io: CoroutineDispatcher
                get() = Dispatchers.IO
            override val default: CoroutineDispatcher
                get() = Dispatchers.Default
        }
    }
    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFetchTopHeadlinesUseCase(newsRepository: NewsRepository) =
        FetchTopHeadlinesUseCase(newsRepository)

}