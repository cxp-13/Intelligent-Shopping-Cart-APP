package com.example.intelligent_shopping_cart.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
@Preview
fun StarRow(star: Int = 3) {
    val starSizeSp = 14.sp
    val density = LocalDensity.current
    val starSize = with(density) { starSizeSp.toDp() }
    Surface() {
        Row() {
            for (i in 0 until 5) {
                Icon(
                    Icons.Outlined.StarOutline,
                    contentDescription = null,
                    modifier = Modifier.size(starSize)
                )
            }
        }
        Row() {
            for (i in 0 until star) {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = null,
                    modifier = Modifier.size(starSize)
                )

            }
        }
    }
}