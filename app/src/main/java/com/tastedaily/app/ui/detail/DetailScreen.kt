@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.tastedaily.app.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.tastedaily.app.ui.components.Format
import com.tastedaily.core.model.ArticleSection
import com.tastedaily.core.model.CookingStep
import com.tastedaily.core.model.Dish
import com.tastedaily.core.model.Ingredient
import com.tastedaily.core.model.VideoAsset

@Composable
fun DetailScreen(
    dishId: String,
    onBack: () -> Unit,
    onStartImmersive: () -> Unit,
    onPlayVideo: (String) -> Unit,
    viewModel: DetailViewModel = viewModel(),
) {
    LaunchedEffect(dishId) { viewModel.load(dishId) }
    val state by viewModel.state.collectAsStateWithLifecycle()
    val dish = state.dish

    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(dish?.name ?: "菜谱详情") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "返回")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                ),
            )
        },
    ) { padding ->
        if (dish == null) return@Scaffold
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(bottom = 24.dp),
        ) {
            item {
                AsyncImage(
                    model = dish.coverImageUrl,
                    contentDescription = dish.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f),
                )
            }
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(dish.name, style = MaterialTheme.typography.headlineMedium)
                    Text(
                        dish.tagline,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    )
                    Spacer(Modifier.height(12.dp))
                    MetaRow(dish)
                    Spacer(Modifier.height(16.dp))
                    Button(
                        onClick = onStartImmersive,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Icon(Icons.Filled.Restaurant, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("沉浸式学做这道菜")
                    }
                }
            }
            item {
                val tabs = listOf("简介", "文章", "视频")
                TabRow(selectedTabIndex = selectedTab) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(title) },
                        )
                    }
                }
            }
            when (selectedTab) {
                0 -> {
                    item { SectionHeader("食材") }
                    items(dish.ingredients, key = { it.name }) { IngredientRow(it) }
                    item { SectionHeader("烹饪步骤") }
                    items(dish.steps, key = { it.index }) { StepRow(it) }
                }
                1 -> {
                    item { SectionHeader(dish.article.title) }
                    item {
                        Text(
                            "文 / ${dish.article.author}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                            modifier = Modifier.padding(horizontal = 16.dp),
                        )
                    }
                    items(dish.article.sections, key = { it.heading }) { ArticleSectionBlock(it) }
                }
                2 -> {
                    items(dish.videos, key = { it.id }) { video ->
                        VideoRow(video = video, onClick = { onPlayVideo(video.id) })
                    }
                }
            }
        }
    }
}

@Composable
private fun MetaRow(dish: Dish) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        MetaChip(dish.cuisine)
        MetaChip(Format.difficultyLabel(dish.difficulty))
        MetaChip(Format.durationLabel(dish.durationMinutes))
        MetaChip(Format.caloriesLabel(dish.calories))
    }
}

@Composable
private fun MetaChip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 10.dp, vertical = 4.dp),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f),
        )
    }
}

@Composable
private fun SectionHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp),
    )
}

@Composable
private fun IngredientRow(ingredient: Ingredient) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(ingredient.name, style = MaterialTheme.typography.bodyLarge)
        Text(
            ingredient.amount,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
        )
    }
}

@Composable
private fun StepRow(step: CookingStep) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(8.dp),
            ) {
                Text(
                    "${step.index}",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(step.title, style = MaterialTheme.typography.titleLarge)
                Text(
                    step.instruction,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
private fun ArticleSectionBlock(section: ArticleSection) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
        Text(section.heading, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(4.dp))
        Text(section.body, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
private fun VideoRow(video: VideoAsset, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clip(RoundedCornerShape(14.dp)),
        onClick = onClick,
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = video.posterImageUrl,
                contentDescription = video.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
            )
            Icon(
                imageVector = Icons.Filled.PlayCircle,
                contentDescription = "播放",
                tint = Color.White.copy(alpha = 0.9f),
                modifier = Modifier
                    .align(Alignment.Center)
                    .height(56.dp)
                    .width(56.dp),
            )
            Text(
                video.title,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp),
            )
        }
    }
}
