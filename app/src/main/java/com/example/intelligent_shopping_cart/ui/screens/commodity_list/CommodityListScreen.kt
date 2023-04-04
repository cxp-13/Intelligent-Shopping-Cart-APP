package com.example.intelligent_shopping_cart.ui.screens.commodity_list

import android.util.Log
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.intelligent_shopping_cart.ui.components.CommoditiesSearchBar
import com.example.intelligent_shopping_cart.view_model.CommodityIntent
import com.example.intelligent_shopping_cart.view_model.CommodityViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommodityListScreen(
    viewModel: CommodityViewModel
) {
    val uiState by viewModel.uiState

    DisposableEffect(key1 = Unit, effect = {
        onDispose {
            uiState.searchBoxValue = ""
        }
    })

    LaunchedEffect(key1 = uiState.searchBoxValue, block = {
        viewModel.initUiState()
        Log.d("zlf", "CommodityListScreen: ${uiState.displayCommodities}")
        Log.d("zlf", "CommodityListScreen: ${uiState.selectedType}")
        Log.d("zlf", "CommodityListScreen: ${uiState.commodityTypeMap}")
    })

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            // 搜索框
            CommoditiesSearchBar(
                value = uiState.searchBoxValue,
                placeholder = "Search for something?",
                changeValue = {
                    viewModel.dispatch(CommodityIntent.ChangeSearchBoxValue(it))
                }
            )
        })
    }) { paddingValues ->
        LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = paddingValues) {
            items(uiState.displayCommodities) { item ->
                CommodityCard(item)
            }
        }

    }
}

