package com.example.newsassignment.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.newsassignment.R
import com.example.newsassignment.presentation.theme.NewsAssignmentTheme

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onArticleClick: (String) -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is HomeViewModel.HomeUiState.Error -> {
            val errorMessage = (uiState as HomeViewModel.HomeUiState.Error).errorMessage
            ErrorScreen(errorMessage = errorMessage)
        }

        is HomeViewModel.HomeUiState.Loading -> {
            LoadingScreen()
        }

        is HomeViewModel.HomeUiState.NoInternet -> {
            NoInternetScreen()
        }

        is HomeViewModel.HomeUiState.Success -> {
            val articlesData = (uiState as HomeViewModel.HomeUiState.Success).articles
            HomeScreen(
                modifier = modifier,
                articles = articlesData,
                onArticleClick = onArticleClick
            )
        }
    }
}

@Composable
private fun ErrorScreen(modifier: Modifier = Modifier, errorMessage: String) {
    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.error))
        LottieAnimation(
            modifier = Modifier
                .weight(4f)
                .align(Alignment.CenterHorizontally),
            composition = composition,
            iterations = LottieConstants.IterateForever
        )

        Text(
            modifier = Modifier.weight(1f),
            text = errorMessage,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onError
        )
    }
}

@Composable
private fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading))
        LottieAnimation(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally),
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
    }
}

@Composable
private fun NoInternetScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.no_internet))

        LottieAnimation(
            modifier = Modifier
                .weight(4f)
                .align(Alignment.CenterHorizontally),
            composition = composition,
            iterations = LottieConstants.IterateForever
        )

        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(id = R.string.no_internet_connection),
            style = MaterialTheme.typography.titleLarge
        )
    }
}


@Preview
@Composable
private fun ErrorScreenPreview() {
    NewsAssignmentTheme {
        ErrorScreen(errorMessage = "Unexpected Error Occurred ")
    }
}

@Preview
@Composable
private fun LoadingScreenPreview() {
    NewsAssignmentTheme() {
        LoadingScreen()
    }
}

@Preview
@Composable
private fun NoInternetScreenPreview() {
    NewsAssignmentTheme {
        NoInternetScreen()
    }
}