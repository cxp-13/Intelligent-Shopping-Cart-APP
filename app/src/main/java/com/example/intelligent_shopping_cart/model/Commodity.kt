package com.example.intelligent_shopping_cart.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "commodity")
data class Commodity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // 商品ID，自动生成的主键
    val name: String = "title null", // 商品名称
    val img: String = "https://images.pexels.com/photos/566345/pexels-photo-566345.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2", // 商品图片ID，使用默认的占位图URL
    val price: Int = 0, // 商品价格
    val origin: String = "origin null", // 商品产地
    val brand: String = "brand null", // 商品品牌
    val specification: String = "specification null", // 商品规格
    @ColumnInfo(name = "shelf_life") val shelfLife: String = "shelfLife null", // 商品保质期，默认为当前时间，使用ColumnInfo注解指定列名为"shelf_life"
    val description: String = "description null", // 商品描述
    val count: Int = 0, // 商品数量
    val type: String = "type null", //商品类别
    var latitude: Double = 0.0, // 商品所在位置的纬度
    var longitude: Double = 0.0, // 商品所在位置的经度
    val location: String = "location null" // 商品所在位置的描述
)


