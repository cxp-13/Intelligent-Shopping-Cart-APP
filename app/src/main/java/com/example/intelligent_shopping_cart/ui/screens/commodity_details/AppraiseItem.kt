package com.example.intelligent_shopping_cart.ui.screens.commodity_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.intelligent_shopping_cart.model.Appraise
import com.example.intelligent_shopping_cart.ui.components.StarRow

@Composable
fun AppraiseItem(modifier: Modifier, appraise: Appraise) {
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(5.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = com.example.intelligent_shopping_cart.R.drawable.ava1),
                contentDescription = null,
                modifier = Modifier
                    .width(50.dp)
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "user title",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = appraise.comment,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = appraise.time,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    StarRow(appraise.star)
                }

            }

        }
    }
}