package com.example.newsassignment.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsassignment.presentation.home.HomeRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsApp(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    Scaffold { innerPadding ->
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
            HomeRoute {url ->
                navHost.navigate(
                    NewsAppDestinations.DetailedNews.passNewsUrl(url)
                )
            }
        }

        composable(route = NewsAppDestinations.DetailedNews.route) {

        }
    }
}