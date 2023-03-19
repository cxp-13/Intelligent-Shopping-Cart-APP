package com.example.intelligent_shopping_cart.screens.main

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chatty.compose.ui.components.CircleShapeImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    drawerState: DrawerState
) {
    val scope = rememberCoroutineScope()
//    val chattyColors = LocalChattyColors.current

    CenterAlignedTopAppBar(
        title = {
            Text(
                "Intelligent_shopping_cart",
                color = MaterialTheme.colorScheme.tertiary
            )
        },
        actions = {
//            IconButton(
//                onClick = { chattyColors.toggleTheme() }
//            ) {
//                Icon(
//                    imageVector = if (chattyColors.isLight)Icons.Rounded.DarkMode else Icons.Rounded.LightMode,
//                    contentDescription = null,
//                    tint = MaterialTheme.colorScheme.onBackground
//                )
//            }
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
                    painter = painterResource(id = com.example.intelligent_shopping_cart.R.drawable.ava4)
                )
            }
        },
        modifier = Modifier.statusBarsPadding()
    )
}
