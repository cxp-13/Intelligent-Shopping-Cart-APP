package com.example.intelligent_shopping_cart.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.intelligent_shopping_cart.ui.screens.map.MapSetting

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FilterChipRow(
    list: List<MapSetting>,
    modifier: Modifier = Modifier,
) {

    FlowRow(modifier = modifier, horizontalArrangement = Arrangement.SpaceAround) {
        list.forEach { tag ->
            FilterChip(
                leadingIcon = { Icon(imageVector = tag.icon, contentDescription = null) },
                label = { Text(tag.name) },
                selected = tag.status,
                onClick = { tag.changeStatus() }
            )
        }

    }
}