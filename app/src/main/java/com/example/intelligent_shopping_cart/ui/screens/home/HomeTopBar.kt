package com.example.intelligent_shopping_cart.ui.screens.home

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.intelligent_shopping_cart.R
import com.example.intelligent_shopping_cart.ui.components.CircleShapeImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun HomeTopBar(
    drawerState: DrawerState,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()

    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(id = R.string.home),
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        keyboardController?.hide()
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
//        modifier = Modifier.statusBarsPadding()
    )
}
