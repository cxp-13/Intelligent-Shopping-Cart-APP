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
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.intelligent_shopping_cart.R
import com.example.intelligent_shopping_cart.ui.screens.main.mock.carouselMapImages
import com.example.intelligent_shopping_cart.ui.screens.main.mock.commodityTypes
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun Main(drawerState: DrawerState) {

    var isOpenSearchBox by remember {
        mutableStateOf(false)
    }

    var searchBoxValue by remember {
        mutableStateOf("")
    }

    Scaffold(
//        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            MainTopBar(drawerState) {
                isOpenSearchBox = !isOpenSearchBox
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            if (isOpenSearchBox) {
                OutlinedTextField(modifier = Modifier.fillMaxWidth(), placeholder = {
                    Text("想找点啥呢？")
                }, value = searchBoxValue, leadingIcon = {
                    Icon(painterResource(id = R.drawable.magic_button), contentDescription = null)
                }, onValueChange = {
                    searchBoxValue = it
                })
            }
            HorizontalPager(count = carouselMapImages.size) {
                Image(
                    painter = painterResource(id = carouselMapImages[it]),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }
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


