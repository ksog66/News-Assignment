package com.example.newsassignment.di

import com.example.newsassignment.data.remote.NewsApi
import com.example.newsassignment.data.repository.NewsRepositoryImpl
import com.example.newsassignment.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideGitTrackerRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository
}