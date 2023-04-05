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

//    companion object {
//        private var INSTANCE: AppDatabase? = null
//
//        // 获取数据库的唯一实例(懒汉式)
//        fun  getInstance(context: Context): AppDatabase? {
//            if (INSTANCE == null) {
//                synchronized(this) {
//                    INSTANCE =
//                        Room.databaseBuilder(
//                            context.applicationContext,
//                            AppDatabase::class.java,
//                            "AppDatabase"
//                        ).build()
//                    return INSTANCE
//                }
//            }
//            return INSTANCE
//        }
//    }
}