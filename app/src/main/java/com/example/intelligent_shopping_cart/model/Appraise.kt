package com.example.intelligent_shopping_cart.model

import java.text.SimpleDateFormat
import java.util.*

data class Appraise(
    val user: User, // 用户个人信息
    val comment: String, // 评价内容
    val time: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Date()), // 评价时间，默认为当前时间
    val star: Int // 商品评星
)