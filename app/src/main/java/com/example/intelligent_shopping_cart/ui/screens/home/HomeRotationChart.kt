package com.example.intelligent_shopping_cart.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.intelligent_shopping_cart.model.Commodity

@Composable
fun HomeRotationChart(items: List<Commodity>) {
    LazyRow() {
        items(items = items, itemContent = {
            RotationChartItem(
                modifier = Modifier.padding(horizontal = 5.dp),
                image = it.img,
                title = it.name
            )
        })
    }
}