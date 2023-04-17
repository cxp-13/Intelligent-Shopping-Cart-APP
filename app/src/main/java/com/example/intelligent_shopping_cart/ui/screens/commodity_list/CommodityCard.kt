package com.example.intelligent_shopping_cart.ui.screens.commodity_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.intelligent_shopping_cart.model.Commodity
import com.example.intelligent_shopping_cart.ui.components.AppScreen
import com.example.intelligent_shopping_cart.utils.LocalNavController

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
fun CommodityCard(modifier: Modifier, commodity: Commodity) {

    val navHostController = LocalNavController.current

    ElevatedCard(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier,
        onClick = {
            navHostController.navigate("${AppScreen.commodityDetail}/${commodity.id}")
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            AsyncImage(
                model = commodity.img,
                contentDescription = null,
                modifier = Modifier
                    .shadow(shape = MaterialTheme.shapes.medium, elevation = 5.dp)
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(5.dp),
                text = commodity.name,
                style = MaterialTheme.typography.headlineSmall,
            )
            Text(
                modifier = Modifier.padding(start = 5.dp, bottom = 5.dp, end = 5.dp),
                text = "$${commodity.price}",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}