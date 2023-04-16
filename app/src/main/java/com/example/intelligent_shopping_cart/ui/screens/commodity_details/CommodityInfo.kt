package com.example.intelligent_shopping_cart.ui.screens.commodity_details

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Paid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.intelligent_shopping_cart.R
import com.example.intelligent_shopping_cart.model.Commodity

// 第一个 Composable 函数，用于显示商品信息
@OptIn(ExperimentalMaterial3Api::class)
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
        Text(
            text = stringResource(id = R.string.commodity_origin, commodity.origin),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = stringResource(id = R.string.commodity_brand, commodity.brand),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = stringResource(
                id = R.string.commodity_specification,
                commodity.specification
            ),
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            text = stringResource(id = R.string.commodity_shelf_life, commodity.shelfLife),
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            text = stringResource(id = R.string.commodity_description, commodity.description),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}


