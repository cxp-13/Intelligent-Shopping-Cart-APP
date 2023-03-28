package com.example.intelligent_shopping_cart.model

import androidx.annotation.DrawableRes
import java.text.SimpleDateFormat
import java.util.*


data class Commodity(
    val name: String, // 商品名称
    @DrawableRes val img: Int, // 商品图片ID
    val price: Int, // 商品价格
    val origin: String, // 商品产地
    val brand: String, // 商品品牌
    val specification: String, // 商品规格
    val shelfLife: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Date()), // 商品保质期，默认为当前时间
    val description: String, // 商品描述
    val appraise: List<Appraise> = emptyList(), // 商品评价列表
    val count: Int, // 商品数量
    val type: String, //商品类别
    val id: String = UUID.randomUUID().toString(),
) {
    val totalPrice = count * price // 商品总价，根据商品数量和单价计算
}
