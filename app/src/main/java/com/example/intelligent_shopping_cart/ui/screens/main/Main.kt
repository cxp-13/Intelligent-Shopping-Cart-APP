package com.example.intelligent_shopping_cart.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.intelligent_shopping_cart.R
import com.example.intelligent_shopping_cart.view_model.CommodityIntent
import com.example.intelligent_shopping_cart.view_model.CommodityViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun Main(drawerState: DrawerState, commodityViewModel: CommodityViewModel) {

    val uiState by commodityViewModel.uiState

    val isOpenSearchBox = uiState.isOpenSearchBox

    val searchBoxValue = uiState.searchBoxValue

    val carouselMapImages = uiState.carouselMapImages

    val commodityTypes = uiState.commodityTypes

    Scaffold(
//        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            MainTopBar(drawerState) {
//                isOpenSearchBox = !isOpenSearchBox
                commodityViewModel.dispatch(CommodityIntent.OpenSearchBox)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
//            搜索框
            if (isOpenSearchBox) {
                OutlinedTextField(modifier = Modifier.fillMaxWidth(), placeholder = {
                    Text("想找点啥呢？")
                }, value = searchBoxValue, leadingIcon = {
                    Icon(painterResource(id = R.drawable.magic_button), contentDescription = null)
                }, onValueChange = {
//                    searchBoxValue = it
                    commodityViewModel.dispatch(CommodityIntent.ChangeSearchBoxValue(it))
                })
            }
//            轮播图
            HorizontalPager(count = carouselMapImages.size) {
                Image(
                    painter = painterResource(id = carouselMapImages[it]),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }
//            商品类型
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
            ) {
                items(commodityTypes) { item ->
                    MainItem(commodityType = item)
                }
            }

        }
    }
}


