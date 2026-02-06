package com.example.movies_presentation.component

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.isScrolledToTheEnd(): Boolean {
    val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
    val totalItemsCount = layoutInfo.totalItemsCount
    return lastVisibleItem != null &&
            lastVisibleItem.index >= totalItemsCount - 1
}