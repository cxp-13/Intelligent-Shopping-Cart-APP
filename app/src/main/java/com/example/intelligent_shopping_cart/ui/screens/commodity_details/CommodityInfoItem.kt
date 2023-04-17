package com.example.intelligent_shopping_cart.ui.screens.commodity_details

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CommodityInfoItem(icon: ImageVector, value: String) {
    AssistChip(
        modifier = Modifier.padding(5.dp),
        onClick = {},
        leadingIcon = {
            Icon(icon, contentDescription = null)

        },
        label = {
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
            )
        }
    )
}