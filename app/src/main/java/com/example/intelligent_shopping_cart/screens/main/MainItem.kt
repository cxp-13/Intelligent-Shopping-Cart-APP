package com.example.intelligent_shopping_cart.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.intelligent_shopping_cart.bean.CommodityType
import com.example.intelligent_shopping_cart.ui.components.AppScreen
import com.example.intelligent_shopping_cart.ui.components.WidthSpacer
import com.example.intelligent_shopping_cart.ui.utils.LocalNavController

@Composable
fun MainItem(commodityType: CommodityType) {

    val navController = LocalNavController.current

    Surface(
        shadowElevation = 5.dp,
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(5.dp)
            .clickable {
                navController.navigate("${AppScreen.commodityList}/${commodityType.name}")
            }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(id = commodityType.image),
                contentDescription = null
            )
            WidthSpacer(value = 10.dp)
            Text(text = commodityType.name)
        }
    }


}