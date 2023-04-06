package com.example.intelligent_shopping_cart.logic.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.intelligent_shopping_cart.logic.dao.CommodityDao
import com.example.intelligent_shopping_cart.logic.dao.UserDao
import com.example.intelligent_shopping_cart.model.Commodity
import com.example.intelligent_shopping_cart.model.User

@Database(entities = [Commodity::class, User::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun commodityDao(): CommodityDao
    abstract fun userDao(): UserDao
}