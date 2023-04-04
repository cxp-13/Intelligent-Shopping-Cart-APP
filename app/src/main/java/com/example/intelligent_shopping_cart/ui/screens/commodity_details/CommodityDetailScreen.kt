package com.example.intelligent_shopping_cart.ui.screens.commodity_details

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.intelligent_shopping_cart.R
import com.example.intelligent_shopping_cart.ui.screens.shopping_cart.mock.appraisesMock
import com.example.intelligent_shopping_cart.view_model.CommodityViewModel

@Composable
fun CommodityDetailScreen(commodityViewModel: CommodityViewModel) {


    val uiState = commodityViewModel.uiState
    val commodity = uiState.value.selectedCommodity


    val scrollState = rememberScrollState()

    Surface() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState)
        ) {
            AsyncImage(
                model = commodity!!.img,
                contentDescription = null,
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
            if (appraisesMock.isNotEmpty()) {
                Column() {
                    appraisesMock.forEach { appraise ->
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