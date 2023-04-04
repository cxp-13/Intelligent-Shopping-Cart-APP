package com.example.intelligent_shopping_cart.ui.screens.shopping_cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.intelligent_shopping_cart.R
import com.example.intelligent_shopping_cart.ui.components.HeightSpacer
import com.example.intelligent_shopping_cart.view_model.CommodityViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingCartScreen(commodityViewModel: CommodityViewModel) {

    val uiState by commodityViewModel.uiState


    LaunchedEffect(key1 = uiState, block = {
        commodityViewModel.initUiState()
    })


//    var shoppingCartCommodityList = remember {
//        mutableStateOf(emptyList<Commodity>())
//    }
//
//    LaunchedEffect(uiState) {
//        commodityViewModel.getAll().collect{
//            shoppingCartCommodityList.value = it.subList(0, 5)
//        }
//    }


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = stringResource(id = R.string.shopping_cart)) })
        },
        floatingActionButton = {
            SettleAccountFAB(uiState.total)
        }
    ) { paddingValues ->
//        已经被放进智能购物车的商品列表
        LazyColumn(contentPadding = paddingValues) {
            items(uiState.placeCommodities) {
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