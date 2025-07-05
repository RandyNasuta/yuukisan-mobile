package org.example.project

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.decodeToImageBitmap
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.example.project.di.createHttpClient
import org.example.project.model.Article
import org.example.project.model.Category
import org.example.project.model.mockArticle
import org.example.project.service.ApiService
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import yuukisanproject.composeapp.generated.resources.Res
import yuukisanproject.composeapp.generated.resources.outline_arrow_forward_24
import kotlin.io.encoding.Base64


@Composable
fun App() {
    MaterialTheme {
        ArticleScreen()
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun ArticleScreen() {
    val scope = rememberCoroutineScope()
    var articles by remember { mutableStateOf(emptyList<Article>()) }
    var categories by remember { mutableStateOf(emptyList<Category>()) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    val apiService = remember { ApiService(createHttpClient(), getPlatform().name.contains("Android")) }

    LaunchedEffect(Unit) {
        scope.launch {
            try {
                articles = apiService.getArticles()
                categories = apiService.getCategories()
            } catch (e: Exception) {
                error = e.message
            } finally {
                isLoading = false
            }
        }
    }

    when {
        isLoading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        error != null -> {
            println(error)
            Text("Error: $error", color = MaterialTheme.colorScheme.error)
        }
        else -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
                            .statusBarsPadding()
            ) {
                items(categories) { category ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    ) {
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                modifier = Modifier.padding(start = 10.dp),
                                text = category.name,
                                style = MaterialTheme.typography.titleLarge,
                            )

                            IconButton(
                                onClick = {}
                            ) {
                                Icon(
                                    painter = painterResource(Res.drawable.outline_arrow_forward_24),
                                    contentDescription = "Next"
                                )
                            }

                        }

                        val articleCategories = articles.filter { it.categoryId == category.id }
                        val pagerState = rememberPagerState(pageCount = { articleCategories.size })

                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                        ) { page ->
                            // Our page content
                            CardArticle(articleCategories[page])
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CardArticle(article: Article) {
    val base64Clean = article.imageBase64.substringAfter("base64,", article.imageBase64)
    val decodedBytes = Base64.decode(base64Clean)
    val imageBitmap = decodedBytes.decodeToImageBitmap()

    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(horizontal = 10.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
        ) {
            Image(
                bitmap = imageBitmap,
                contentDescription = "Cover",
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(12.dp),
                text = article.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview
@Composable
fun CardArticlePreview() {
    MaterialTheme {
        CardArticle(mockArticle)
    }
}