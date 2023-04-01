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
fun CommodityList(
    commodityTypeId: String?,
    viewModel: CommodityViewModel
) {
    val uiState by viewModel.uiState

//    val list = uiState.showCommodities!!.toMutableStateList()
//    var list: SnapshotStateList<Commodity> = remember {
//        uiState.showCommodities!!.toMutableStateList()
//    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            // 搜索框
            CommoditiesSearchBar(
                value = uiState.searchBoxValue,
                placeholder = "Search for something?",
                changeValue = {
                    viewModel.dispatch(CommodityIntent.ChangeSearchBoxValue(it))
                }
            ) {
                viewModel.dispatch(CommodityIntent.SearchBtnClick)
                Log.d("test", "CommodityList: ${uiState.showCommodities}")

//                val list1 = list.filter {
//                    it.name.contains(uiState.searchBoxValue)
//                }.toMutableStateList()
//
//                Log.d("cxp", "CommodityList: ${list1.toList()}")
//
//                list = list1
            }
//            Text(text = stringResource(id = R.string.commodity_list))
        })
    }) { paddingValues ->
        LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = paddingValues) {
            items(uiState.showCommodities) { item ->
                CommodityCard(commodityTypeId, item)
            }
        }

    }
}

