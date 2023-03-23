package com.example.intelligent_shopping_cart.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.intelligent_shopping_cart.R

@Composable
fun MyBottomNavigationBar(
    selectedScreen: Int,
    onClick: (targetIndex: Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier.navigationBarsPadding()
    ) {
        BottomScreen.values().forEachIndexed { index, screen ->
            NavigationBarItem(
                selected = selectedScreen == index,
                onClick = { onClick(index) },
                icon = {
                    Icon(
                        imageVector = if (selectedScreen == index) screen.selectedIcon else screen.unselectedIcon,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(stringResource(id = screen.label)) }
            )
        }
    }
}

enum class BottomScreen(
    @StringRes val label: Int,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
) {
    Main(R.string.main, Icons.Outlined.Home, Icons.Filled.Home),
    ShoppingCart(R.string.shopping_cart, Icons.Outlined.ShoppingCart, Icons.Filled.ShoppingCart)

}

object AppScreen {
    const val login = "login"
    const val main = "main"
    const val register = "register"
    const val shoppingCart = "shoppingCart"
    const val profileEdit = "profile_edit"
}
