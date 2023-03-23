package com.example.intelligent_shopping_cart.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.intelligent_shopping_cart.screens.main.Main
import com.example.intelligent_shopping_cart.screens.personal.PersonalProfile
import com.example.intelligent_shopping_cart.screens.shopping_cart.ShoppingCart
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun AppScaffold() {

    val snackbarHostState = remember { SnackbarHostState() }
//    val scaffoldState = rememberScaffoldState()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    var selectedScreen by remember { mutableStateOf(0) }

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                PersonalProfile(snackbarHostState)
            }

        },
        drawerState = drawerState,
        modifier = Modifier.navigationBarsPadding()
    ) {
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
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
                    BottomScreen.Main -> Main(drawerState)
                    BottomScreen.ShoppingCart -> ShoppingCart()
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
