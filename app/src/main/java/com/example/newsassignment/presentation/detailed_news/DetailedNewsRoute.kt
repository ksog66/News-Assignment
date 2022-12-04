package com.example.newsassignment.presentation.detailed_news

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun DetailedNewsRoute(
    modifier: Modifier = Modifier,
    url: String
) {

    AndroidView(
        modifier = modifier.padding(top = 5.dp),
        factory = {

            WebView(it).apply {

                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        }, update = {
            it.loadUrl(url)
        })
}