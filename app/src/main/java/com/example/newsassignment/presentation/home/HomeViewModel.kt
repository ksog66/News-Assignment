package com.example.newsassignment.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsassignment.common.Resource
import com.example.newsassignment.domain.NetworkInfoProvider
import com.example.newsassignment.domain.model.Article
import com.example.newsassignment.domain.use_case.FetchTopHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchTopHeadlinesUseCase: FetchTopHeadlinesUseCase,
    private val networkInfoProvider: NetworkInfoProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        fetchTopHeadlines()
    }

    fun fetchTopHeadlines() {
        viewModelScope.launch {
            if (!networkInfoProvider.hasNetwork()) {
                _uiState.value = HomeUiState.NoInternet
            } else {
                fetchTopHeadlinesUseCase.perform().collectLatest {result ->
                    _uiState.value = when (result) {
                        is Resource.Error -> HomeUiState.Error(errorMessage = result.errorMessage!!)
                        is Resource.Loading -> HomeUiState.Loading
                        is Resource.Success -> HomeUiState.Success(articles = result.data ?: listOf())
                    }
                }
            }
        }
    }


    sealed interface HomeUiState {
        object Loading : HomeUiState

        data class Success(
            val articles: List<Article>
        ) : HomeUiState

        data class Error(
            val errorMessage: String
        ) : HomeUiState

        object NoInternet: HomeUiState
    }
}