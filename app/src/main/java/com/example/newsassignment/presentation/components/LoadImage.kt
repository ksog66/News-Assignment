package com.example.newsassignment.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun LoadImage(
    modifier: Modifier,
    url: String,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    shape : Shape = RoundedCornerShape(5.dp)
) {
    Surface(color = Color.Transparent, shape = shape) {
        AsyncImage(
            model = url,
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = modifier.fillMaxSize()
        )
    }
}