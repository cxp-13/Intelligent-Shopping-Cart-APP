package com.example.intelligent_shopping_cart.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommoditiesSearchBar(
    value: String,
    placeholder: String,
    changeValue: (String) -> Unit,
) {
    SearchBar(
        modifier = Modifier
            .fillMaxWidth(),
        query = value,
        placeholder = { Text(placeholder) },
        onQueryChange = {
            changeValue(it)
        },
        onSearch = {

        },
        active = false,
        onActiveChange = {},
        leadingIcon = {
            Icon(Icons.Rounded.Search, contentDescription = null)
        }) {}
}