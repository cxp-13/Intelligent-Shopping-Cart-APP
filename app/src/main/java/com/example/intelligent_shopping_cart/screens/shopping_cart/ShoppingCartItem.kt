package com.example.intelligent_shopping_cart.screens.shopping_cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.intelligent_shopping_cart.R
import com.example.intelligent_shopping_cart.bean.Commodity
import com.example.intelligent_shopping_cart.ui.components.AppScreen
import com.example.intelligent_shopping_cart.ui.components.HeightSpacer
import com.example.intelligent_shopping_cart.ui.components.NumberChips
import com.example.intelligent_shopping_cart.ui.components.WidthSpacer
import com.example.intelligent_shopping_cart.ui.utils.LocalNavController

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
            navHostController.navigate("${AppScreen.commodityDetail}/${commodity.name}")
        }


    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp), verticalAlignment = Alignment.CenterVertically
        ) {

            Card(modifier = Modifier.weight(0.3f), shape = MaterialTheme.shapes.extraSmall) {
                Image(
                    painterResource(id = commodity.img),
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
                        text = "数量",
                        style = MaterialTheme.typography.labelSmall
                    )
                    NumberChips(number = commodity.count)
                }
                HeightSpacer(value = 3.dp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        modifier = Modifier.padding(end = 3.dp),
                        text = "单价",
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
            NumberChips(number = commodity.totalPrice, color = Color.Blue)
        }
    }
}

@Preview
@Composable
fun ShoppingCartItemPreview() {
    // 生成一个名为"苹果"，价格为5元，数量为2个的Commodity对象
    val apple = Commodity(
        "苹果",
        R.drawable.ava3,
        5,
        "中国",
        "红富士",
        "10斤装",
        "2021-12-31 23:59:59.999",
        "新鲜的苹果",
        listOf(),
        2
    )

    ShoppingCartItem(
        apple, Modifier
            .height(100.dp)
            .fillMaxWidth()
    )
}