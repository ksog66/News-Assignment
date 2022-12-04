package com.example.newsassignment.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsassignment.R
import com.example.newsassignment.presentation.detailed_news.DetailedNewsRoute
import com.example.newsassignment.presentation.home.HomeRoute
import com.example.newsassignment.utils.encodeUrl

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsApp(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.news_break),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            )
        }
    ) { innerPadding ->
        NewsAppNavHost(
            modifier = modifier.padding(innerPadding),
            navHost = navController
        )
    }

}

@Composable
fun NewsAppNavHost(modifier: Modifier = Modifier, navHost: NavHostController) {
    NavHost(
        navController = navHost,
        startDestination = NewsAppDestinations.Home.route
    ) {
        composable(route = NewsAppDestinations.Home.route) {
            HomeRoute(modifier = modifier) { url ->
                navHost.navigate(
                    NewsAppDestinations.DetailedNews.passNewsUrl(url.encodeUrl())
                )
            }
        }

        composable(
            route = NewsAppDestinations.DetailedNews.route,
            arguments = listOf(navArgument(NEWS_URL_ARGUMENT_KEY) {
                type = NavType.StringType
            })
        ) {
            val url = it.arguments?.getString(NEWS_URL_ARGUMENT_KEY) ?: ""
            DetailedNewsRoute(url = url)
        }
    }
}