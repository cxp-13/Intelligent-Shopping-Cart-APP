package com.example.intelligent_shopping_cart.ui.screens.shopping_cart

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.intelligent_shopping_cart.R
import com.example.intelligent_shopping_cart.model.Commodity
import com.example.intelligent_shopping_cart.ui.components.AppScreen
import com.example.intelligent_shopping_cart.ui.components.HeightSpacer
import com.example.intelligent_shopping_cart.ui.components.NumberChips
import com.example.intelligent_shopping_cart.ui.components.WidthSpacer
import com.example.intelligent_shopping_cart.utils.LocalNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingCartItem(commodity: Commodity, modifier: Modifier) {

    val navHostController = LocalNavController.current

    Surface(
        shape = MaterialTheme.shapes.extraSmall,
        modifier = modifier,
        shadowElevation = 5.dp,
        color = MaterialTheme.colorScheme.tertiary,
        contentColor = MaterialTheme.colorScheme.onTertiary,
        onClick = {
            navHostController.navigate("${AppScreen.commodityDetail}/${commodity.id}")
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp), verticalAlignment = Alignment.CenterVertically
        ) {

            Card(modifier = Modifier.weight(0.3f), shape = MaterialTheme.shapes.extraSmall) {
                AsyncImage(
                    model = commodity.img,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }
            WidthSpacer(value = 10.dp)
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.7f)
            ) {
                Text(text = commodity.name, style = MaterialTheme.typography.titleLarge)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        modifier = Modifier.padding(end = 3.dp),
                        text = stringResource(id = R.string.specification),
                        style = MaterialTheme.typography.labelSmall
                    )
                    NumberChips(number = commodity.count)
                }
                HeightSpacer(value = 3.dp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        modifier = Modifier.padding(end = 3.dp),
                        text = stringResource(id = R.string.price),
                        style = MaterialTheme.typography.labelSmall
                    )
                    NumberChips(number = commodity.price)
                }
            }
            Text(
                text = "$",
                modifier = Modifier.padding(end = 3.dp),
                style = MaterialTheme.typography.labelSmall
            )
            NumberChips(number = 0, color = Color.Blue)
        }
    }
}
