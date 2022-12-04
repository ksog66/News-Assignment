package com.example.newsassignment.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.newsassignment.R
import com.example.newsassignment.domain.model.Article
import com.example.newsassignment.presentation.components.LoadImage


@Composable
fun HomeScreen(modifier: Modifier = Modifier, articles: List<Article>,onArticleClick: (String) -> Unit) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn {
            items(items = articles) { article ->
                ArticleDisplay(article = article) {
                    onArticleClick(article.newsUrl)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDisplay(
    modifier: Modifier = Modifier,
    article: Article,
    onArticleClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = modifier.padding(5.dp),
        onClick = onArticleClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            LoadImage(
                modifier = Modifier.requiredSize(100.dp),
                url = article.coverImageUrl
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier.requiredHeightIn(min = 100.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Column {


                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = article.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = stringResource(id = R.string.by_author, article.author),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

    }
}