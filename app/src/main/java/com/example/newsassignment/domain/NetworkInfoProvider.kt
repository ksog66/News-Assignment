package com.example.newsassignment.domain

import kotlinx.coroutines.flow.Flow

interface NetworkInfoProvider {
    fun listenToChanges(): Flow<Boolean>
    fun hasNetwork(): Boolean
}