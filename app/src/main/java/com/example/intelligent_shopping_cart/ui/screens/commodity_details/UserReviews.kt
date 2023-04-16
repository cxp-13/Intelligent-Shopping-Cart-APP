package com.example.intelligent_shopping_cart.ui.screens.commodity_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.intelligent_shopping_cart.R
import com.example.intelligent_shopping_cart.model.Appraise
import com.example.intelligent_shopping_cart.ui.screens.shopping_cart.mock.appraisesMock

// 第二个 Composable 函数，用于显示用户评论列表
@Composable
fun UserReviews(appraises: List<Appraise> = appraisesMock) {
    Text(
        text = stringResource(id = R.string.user_reviews),
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    if (appraises.isNotEmpty()) {
        Column() {
            appraises.forEach { appraise ->
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