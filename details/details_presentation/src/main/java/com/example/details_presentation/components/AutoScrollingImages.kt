package com.example.details_presentation.components

import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay

@Composable
fun AutoScrollingImages(
    modifier: Modifier = Modifier,
    items: List<String>
) {
    val pagerState = rememberPagerState(pageCount = { items.size })
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    LaunchedEffect(pagerState.settledPage, isDragged) {
        if (!isDragged) {
            delay(5000L)
            val nextPage = (pagerState.currentPage + 1) % items.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = items[page],
                contentDescription = items[page],
                contentScale = ContentScale.Crop
            )
        }
    }
}