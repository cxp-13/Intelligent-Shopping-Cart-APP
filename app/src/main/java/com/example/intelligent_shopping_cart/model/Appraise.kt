package com.example.intelligent_shopping_cart.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appraise")
data class Appraise(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "user_id") val userId: Int = 0, // 用户个人id
    @ColumnInfo(name = "commodity_id") val commodityId: Int = 0,
    val comment: String = "comment null", // 评价内容
    val time: String = "time null", // 评价时间，默认为当前时间
    val star: Int = 0// 商品评星
)