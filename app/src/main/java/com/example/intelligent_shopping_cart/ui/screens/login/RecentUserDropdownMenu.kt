package com.example.intelligent_shopping_cart.ui.screens.login

import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.intelligent_shopping_cart.model.User

@Composable
fun RecentUserDropdownMenu(
    modifier: Modifier,
    expanded: Boolean,
    recentUserList: List<User>,
    closeDropMenu: () -> Unit,
    itemClick: (user: User) -> Unit
) {
    DropdownMenu(
        modifier = modifier,
        expanded = expanded,
        onDismissRequest = { closeDropMenu() }
    ) {
        recentUserList.forEach {
            RecentUserDropdownMenuItem(Modifier, it, itemClick, closeDropMenu)
        }
    }
}