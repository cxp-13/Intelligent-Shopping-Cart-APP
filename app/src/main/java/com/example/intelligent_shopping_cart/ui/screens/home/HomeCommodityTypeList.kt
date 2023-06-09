package com.example.intelligent_shopping_cart.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.intelligent_shopping_cart.model.Commodity

@Composable
fun HomeCommodityTypeList(
    items: Map<String, List<Commodity>>,
    navToCommodityList: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp),
    ) {
        items.forEach { (typeName, commodities) ->
            HomeCommodityTypeListItem(typeName, commodities[0]) {
                navToCommodityList(typeName)
            }
        }
    }
}