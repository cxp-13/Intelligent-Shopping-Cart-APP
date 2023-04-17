package com.example.intelligent_shopping_cart.ui.screens.commodity_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.intelligent_shopping_cart.ui.components.CommoditiesSearchBar
import com.example.intelligent_shopping_cart.view_model.CommodityIntent
import com.example.intelligent_shopping_cart.view_model.CommodityViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CommodityListScreen(
    viewModel: CommodityViewModel
) {

    val uiState by viewModel.uiState.collectAsState()

    DisposableEffect(key1 = Unit, effect = {
        onDispose {
            uiState.searchBoxValue = ""
        }
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
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = paddingValues,
        ) {
            items(uiState.displayCommodities, key = {
                it.id
            }) { item ->
                CommodityCard(
                    Modifier
                        .animateItemPlacement()
                        .padding(10.dp), item)
            }
        }
    }
}

