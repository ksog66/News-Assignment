package com.example.newsassignment.di

import com.example.newsassignment.NetworkInfoProviderImpl
import com.example.newsassignment.data.repository.NewsRepositoryImpl
import com.example.newsassignment.domain.NetworkInfoProvider
import com.example.newsassignment.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    @Singleton
    abstract fun provideGitTrackerRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository

    @Binds
    @Singleton
    abstract fun provideNetworkInfoProviderImpl(networkInfoProvider: NetworkInfoProviderImpl): NetworkInfoProvider
}