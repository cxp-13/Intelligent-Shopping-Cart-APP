package com.example.intelligent_shopping_cart.ui.screens.map

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.intelligent_shopping_cart.ui.components.FilterChipRow
import com.example.intelligent_shopping_cart.view_model.TencentMapIntent
import com.example.intelligent_shopping_cart.view_model.TencentMapLayer
import com.example.intelligent_shopping_cart.view_model.TencentMapUiState
import com.example.intelligent_shopping_cart.view_model.TencentMapViewModel


@Composable
fun MapSettingsCard(
    settings: List<MapSetting>,
    uiState: TencentMapUiState,
    tencentViewModel: TencentMapViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            FilterChipRow(settings, Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Map layer selection")
            Row(modifier = Modifier.fillMaxWidth()) {
                for (mapLayer in TencentMapLayer.values()) {
                    IconButton(
                        onClick = {
                            tencentViewModel.dispatch(
                                TencentMapIntent.ToggleMapType(
                                    mapLayer.type
                                )
                            )
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = mapLayer.icon,
                            contentDescription = null,
                            tint = if (uiState.selectedMapType == mapLayer.type) Color.Blue else Color.Gray
                        )
                    }
                }
            }
        }
    }
}
