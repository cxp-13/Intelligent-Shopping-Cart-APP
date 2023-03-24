package com.example.intelligent_shopping_cart.screens.commodity_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.intelligent_shopping_cart.bean.Commodity
import com.example.intelligent_shopping_cart.screens.shopping_cart.mock.commodities


@Preview
@Composable
fun CommodityListPreview() {
    CommodityList(commodities)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommodityList(commodities: List<Commodity>) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(text = "商品列表")
        })
    }) { paddingValues ->
        LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = paddingValues) {
            items(commodities.size) { index ->
                CommodityCard(commodities[index])
            }
        }
    }
}

@Composable
fun CommodityCard(commodity: Commodity) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = commodity.img),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = commodity.name,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "$${commodity.price}",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}