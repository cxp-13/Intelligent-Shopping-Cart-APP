package com.example.intelligent_shopping_cart.ui.screens.shopping_cart

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.intelligent_shopping_cart.R
import com.example.intelligent_shopping_cart.ui.components.WidthSpacer

@Composable
@Preview
fun SettleAccountFAB(totalAmount: Int = 0) {


    val interactionSource = remember { MutableInteractionSource() }
    val isPressedAsState by interactionSource.collectIsPressedAsState()

    ExtendedFloatingActionButton(onClick = { /*TODO*/ }, interactionSource = interactionSource) {
        Row() {

            if (!isPressedAsState) {
                Icon(
                    painter = painterResource(id = R.drawable.settle_account),
                    contentDescription = null
                )
                WidthSpacer(value = 3.dp)
                Text(
                    text = "结算",
                )

            } else {
                Text(
                    text = "总价：$totalAmount",
                )
            }

        }

    }
}