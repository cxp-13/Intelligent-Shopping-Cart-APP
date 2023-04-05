package com.example.intelligent_shopping_cart.ui.screens.login

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.intelligent_shopping_cart.model.User
import com.example.intelligent_shopping_cart.ui.components.CircleShapeImage

@Composable
fun RecentUserDropdownMenuItem(
    modifier: Modifier,
    user: User,
    itemClick: (user: User) -> Unit,
    closeDropMenu: () -> Unit
) {
    DropdownMenuItem(
        modifier = modifier,
        text = { Text(user.nickname) },
        onClick = {
            itemClick(user)
            closeDropMenu()
        },
        leadingIcon = {
            CircleShapeImage(size = 45.dp, img = user.avatar)
        })
}