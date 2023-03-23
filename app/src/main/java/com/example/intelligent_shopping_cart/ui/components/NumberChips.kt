package com.example.intelligent_shopping_cart.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NumberChips(
    number: Int,
    color: Color = Color.Red
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = color,
    ) {
        Text(
            text = number.toString(),
            modifier = Modifier.padding(
                start = 6.dp,
                end = 6.dp,
                top = 2.dp,
                bottom = 2.dp
            ),
            style = androidx.compose.material3.MaterialTheme.typography.labelSmall.copy(color = Color.White)
        )
    }
}
