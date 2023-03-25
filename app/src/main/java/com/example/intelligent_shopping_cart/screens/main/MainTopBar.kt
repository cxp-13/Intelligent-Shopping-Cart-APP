package com.example.intelligent_shopping_cart.screens.main

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.intelligent_shopping_cart.R
import com.example.intelligent_shopping_cart.ui.components.CircleShapeImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    drawerState: DrawerState,
    openSearchBox: () -> Unit
) {
    val scope = rememberCoroutineScope()
//    val chattyColors = LocalChattyColors.current
    CenterAlignedTopAppBar(
        title = {
            Text(
                "首页",
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            IconButton(
                onClick = { openSearchBox() }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                },
            ) {
                CircleShapeImage(
                    size = 40.dp,
                    painter = painterResource(id = R.drawable.ava4)
                )
            }
        },
        modifier = Modifier.statusBarsPadding()
    )
}
