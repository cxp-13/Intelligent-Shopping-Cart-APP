package com.example.intelligent_shopping_cart.ui.screens.commodity_details

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.intelligent_shopping_cart.model.Commodity

// 第一个 Composable 函数，用于显示商品信息
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CommodityInfo(commodity: Commodity) {

    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = commodity.name,
                style = MaterialTheme.typography.titleLarge,
            )
            Badge {
                Icon(Icons.Rounded.Paid, contentDescription = null)

                Text(
                    text = "${commodity.price}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        )
        FlowRow(horizontalArrangement = Arrangement.Start) {
            CommodityInfoItem(icon = Icons.Rounded.TripOrigin, value = commodity.origin)
            CommodityInfoItem(icon = Icons.Rounded.BrandingWatermark, value = commodity.brand)
            CommodityInfoItem(icon = Icons.Rounded.FolderSpecial, value = commodity.specification)
            CommodityInfoItem(icon = Icons.Rounded.Timelapse, value = commodity.shelfLife)
            CommodityInfoItem(icon = Icons.Rounded.Description, value = commodity.description)
        }


    }
}


