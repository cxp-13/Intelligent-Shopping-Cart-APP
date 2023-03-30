package com.example.intelligent_shopping_cart.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.intelligent_shopping_cart.model.CommodityType
import com.example.intelligent_shopping_cart.ui.components.AppScreen
import com.example.intelligent_shopping_cart.utils.LocalNavController

@Composable
fun MainItem(commodityType: CommodityType) {

    val navController = LocalNavController.current

//    Surface(
//        color = MaterialTheme.colorScheme.primaryContainer,
//        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
//        shadowElevation = 5.dp,
//        shape = MaterialTheme.shapes.medium,
//        modifier = Modifier
//            .padding(5.dp)
//            .clickable {
//                navController.navigate("${AppScreen.commodityList}/${commodityType.id}")
//            }
//    ) {
//        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            Image(
//                modifier = Modifier
//                    .size(100.dp)
//                    .clip(MaterialTheme.shapes.medium),
//                painter = painterResource(id = commodityType.image),
//                contentDescription = null
//            )
//            WidthSpacer(value = 10.dp)
//            Text(text = commodityType.name, style = MaterialTheme.typography.bodyLarge)
//        }
//    }

    Surface(
        shape = MaterialTheme.shapes.large,
        shadowElevation = 10.dp,
        tonalElevation = 10.dp,
        modifier = Modifier
            .height(100.dp)
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.clickable {
                navController.navigate("${AppScreen.commodityList}/${commodityType.id}")
            }
        ) {
            Image(
                painter = painterResource(id = commodityType.image),
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.padding(horizontal = 12.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = commodityType.name,
                    style = MaterialTheme.typography.bodyLarge
                )
//                Spacer(Modifier.padding(vertical = 8.dp))
                Text(
                    text = "礼谙"
                )
            }
        }
    }
}