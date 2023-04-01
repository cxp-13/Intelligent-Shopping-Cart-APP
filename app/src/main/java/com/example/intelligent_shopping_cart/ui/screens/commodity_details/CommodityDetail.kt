package com.example.intelligent_shopping_cart.ui.screens.commodity_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.intelligent_shopping_cart.R
import com.example.intelligent_shopping_cart.model.Commodity
import com.example.intelligent_shopping_cart.view_model.shoppingCartCommodityListMock

@Composable
fun CommodityDetail(commodity: Commodity) {

    val scrollState = rememberScrollState()

    Surface() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState)
        ) {
            Image(
                painter = painterResource(id = commodity.img),
                contentDescription = commodity.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(16.dp))
            )
            Text(
                text = stringResource(id = R.string.commodity_name, commodity.name),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Text(
                text = stringResource(id = R.string.commodity_price, commodity.price),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(id = R.string.commodity_origin, commodity.origin),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = stringResource(id = R.string.commodity_brand, commodity.brand),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
            }
            Text(
                text = stringResource(
                    id = R.string.commodity_specification,
                    commodity.specification
                ),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = stringResource(id = R.string.commodity_shelf_life, commodity.shelfLife),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = stringResource(id = R.string.commodity_description, commodity.description),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = stringResource(id = R.string.user_reviews),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            if (commodity.appraise.isNotEmpty()) {
                Column() {
                    commodity.appraise.forEach { appraise ->
                        AppraiseItem(Modifier.padding(10.dp), appraise)
                    }
                }
            } else {
                Text(
                    text = stringResource(id = R.string.no_views),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
    }

}



@Composable
@Preview
fun CommodityDetailPagePreview() {
    CommodityDetail(commodity = shoppingCartCommodityListMock[0])

}