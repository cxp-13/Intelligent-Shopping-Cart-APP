package com.example.intelligent_shopping_cart.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.intelligent_shopping_cart.model.Commodity

@Composable
fun HomeCommodityTypeListItem(typeName: String, commodity: Commodity, navToList: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navToList()
            }
    ) {
        CommodityTypeImage(commodity)
        Column {
            TitleDescription(typeName, commodity)
            Divider()
        }
    }
}

@Composable
private fun TitleDescription(typeName: String, commodity: Commodity) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TitleAndDescription(typeName, commodity)
    }
}

@Composable
private fun RowScope.TitleAndDescription(typeName: String, commodity: Commodity) {
    Column(
        modifier = Modifier
            .weight(1F)
    ) {
        Text(
            text = typeName,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 24.dp)
        )
        Text(
            text = commodity.description,
            style = MaterialTheme.typography.labelSmall.copy(),
            modifier = Modifier
                .paddingFromBaseline(bottom = 24.dp),
            color = Color.Black.copy(alpha = 0.5f)
        )
    }
}

@Composable
private fun CommodityTypeImage(commodity: Commodity) {
    AsyncImage(
        model = commodity.img,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(64.dp)
            .clip(MaterialTheme.shapes.small),
    )
}
