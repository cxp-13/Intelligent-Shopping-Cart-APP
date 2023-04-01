package com.example.intelligent_shopping_cart.ui.screens.commodity_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.intelligent_shopping_cart.model.Commodity
import com.example.intelligent_shopping_cart.ui.components.AppScreen
import com.example.intelligent_shopping_cart.utils.LocalNavController

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun CommodityCard(commodityTypeId: String?, commodity: Commodity) {

    val navHostController = LocalNavController.current

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(8.dp),
        onClick = {
            navHostController.navigate("${AppScreen.commodityDetail}/${commodity.id}?commodityTypeId=${commodityTypeId}")
        }
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
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "$${commodity.price}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}