package com.example.newsassignment.presentation


const val NEWS_URL_ARGUMENT_KEY = "news_url"

sealed class NewsAppDestinations(val route: String) {
    object Home: NewsAppDestinations(route = "home_screen")

    object DetailedNews: NewsAppDestinations(route = "detailed_news/{$NEWS_URL_ARGUMENT_KEY}") {
        fun passNewsUrl(newsUrl: String) : String {
            return this.route.replace(
                oldValue = "{$NEWS_URL_ARGUMENT_KEY}",
                newValue = newsUrl
            )
        }
    }
}