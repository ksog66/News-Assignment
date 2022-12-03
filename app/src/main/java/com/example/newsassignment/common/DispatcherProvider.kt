package com.example.newsassignment.common

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val main: CoroutineDispatcher
    val io:CoroutineDispatcher
    val default: CoroutineDispatcher
}