package com.example.intelligent_shopping_cart.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(drawerState: DrawerState) {
    Scaffold(
        topBar = {
            MainTopBar(drawerState)
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Text(text = "main")
        }

    }
}