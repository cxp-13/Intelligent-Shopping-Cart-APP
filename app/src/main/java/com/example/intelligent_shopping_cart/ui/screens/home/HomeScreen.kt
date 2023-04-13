package com.example.intelligent_shopping_cart.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.intelligent_shopping_cart.R
import com.example.intelligent_shopping_cart.ui.components.AppScreen
import com.example.intelligent_shopping_cart.ui.components.HeightSpacer
import com.example.intelligent_shopping_cart.utils.LocalNavController
import com.example.intelligent_shopping_cart.view_model.CommodityViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(drawerState: DrawerState, commodityViewModel: CommodityViewModel) {

    val navHostController = LocalNavController.current
    val uiState by commodityViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            HomeTopBar(drawerState)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(id = R.string.hot_today),
                modifier = Modifier.paddingFromBaseline(top = 32.dp),
                style = MaterialTheme.typography.titleLarge
            )
            HeightSpacer(value = 10.dp)
//            轮播图
//            HomeRotationChart(items = uiState.carouselItems)
            HomeRotationChart(items = uiState.carouselItems)
            Text(
                text = stringResource(id = R.string.list_of_goods),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .paddingFromBaseline(
                        top = 40.dp,
                        bottom = 16.dp,
                    )
            )
//            商品类型列表
            HomeCommodityTypeList(items = uiState.commodityTypeMap) {
                navHostController.navigate("${AppScreen.commodityList}/${it}")
            }
        }
    }
}


