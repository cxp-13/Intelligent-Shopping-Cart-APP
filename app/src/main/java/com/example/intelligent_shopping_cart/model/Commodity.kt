package com.example.intelligent_shopping_cart.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "commodity")
data class Commodity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String = "name null", // 商品名称
    val img: String = "https://images.pexels.com/photos/566345/pexels-photo-566345.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2", // 商品图片ID
    val price: Int = 0, // 商品价格
    val origin: String = "origin null", // 商品产地
    val brand: String = "brand null", // 商品品牌
    val specification: String = "specification null", // 商品规格
    @ColumnInfo(name = "shelf_life") val shelfLife: String = "shelfLife null", // 商品保质期，默认为当前时间
    val description: String = "description null", // 商品描述
//    val appraise: List<Appraise> = emptyList(), // 商品评价列表
    val count: Int = 0, // 商品数量
    val type: String = "type null", //商品类别
)

