package com.example.intelligent_shopping_cart.model

import androidx.annotation.DrawableRes
import java.util.*

data class CommodityType(
    @DrawableRes val image: Int,
    val name: String,
    val commodities: List<Commodity>,
    val id: String = UUID.randomUUID().toString(),
    val description: String,

    )
