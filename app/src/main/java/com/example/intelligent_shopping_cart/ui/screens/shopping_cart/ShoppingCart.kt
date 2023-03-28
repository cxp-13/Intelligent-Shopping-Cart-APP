package com.example.intelligent_shopping_cart.ui.screens.shopping_cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.intelligent_shopping_cart.ui.components.HeightSpacer
import com.example.intelligent_shopping_cart.ui.screens.main.mock.commodityTypes
import com.example.intelligent_shopping_cart.ui.screens.shopping_cart.mock.commoditiesDefault


val sumPrice = commoditiesDefault.sumOf {
    it.totalPrice
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun ShoppingCart() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "购物车") })
        },
        floatingActionButton = {
            SettleAccountFAB(sumPrice)
        }
    ) { paddingValues ->
        Column() {
            LazyColumn(contentPadding = paddingValues, modifier = Modifier.weight(9.2f)) {
                items(commodityTypes[0].commodities) {
                    ShoppingCartItem(
                        commodity = it, Modifier
                            .height(100.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp)

                    )
                    HeightSpacer(value = 3.dp)
                }
            }

        }
    }
}