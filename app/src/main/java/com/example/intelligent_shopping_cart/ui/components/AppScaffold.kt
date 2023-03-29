package com.example.intelligent_shopping_cart.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.example.intelligent_shopping_cart.ui.screens.main.Main
import com.example.intelligent_shopping_cart.ui.screens.personal.PersonalProfile
import com.example.intelligent_shopping_cart.ui.screens.shopping_cart.ShoppingCart
import com.example.intelligent_shopping_cart.view_model.CommodityViewModel
import com.example.intelligent_shopping_cart.view_model.UserViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AppScaffold(userViewModel: UserViewModel, commodityViewModel: CommodityViewModel) {

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    var selectedScreen by remember { mutableStateOf(0) }

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                PersonalProfile(userViewModel)
            }
        },
        drawerState = drawerState,
    ) {
        Scaffold(
            contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
            topBar = {
            },
            bottomBar = {
                MyBottomNavigationBar(
                    selectedScreen = selectedScreen,
                    onClick = {
                        scope.launch {
                            pagerState.scrollToPage(it)
                        }
                    }
                )
            }
        ) {
            HorizontalPager(
                count = BottomScreen.values().size,
                state = pagerState,
                userScrollEnabled = false,
                contentPadding = it
            ) { page ->
                when (BottomScreen.values()[page]) {
                    BottomScreen.Main -> Main(drawerState, commodityViewModel)
                    BottomScreen.ShoppingCart -> ShoppingCart(commodityViewModel)
                }
            }
        }
    }
//更新当前选中的page下标
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            selectedScreen = page
        }
    }

    BackHandler(drawerState.isOpen) {
        scope.launch {
            drawerState.close()
        }
    }
}
