package com.example.intelligent_shopping_cart.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage

@Composable
fun CircleShapeImage(
    size: Dp,
    img: String,
    contentScale: ContentScale = ContentScale.FillBounds
) {
    Surface(
        modifier = Modifier
            .size(size),
        shape = CircleShape
    ) {
        AsyncImage(
            model = img,
            contentDescription = null,
            contentScale = contentScale
        )
    }
}
