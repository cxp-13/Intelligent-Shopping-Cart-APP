package com.example.intelligent_shopping_cart.logic.config

import android.content.Context
import androidx.room.Room
import com.example.intelligent_shopping_cart.logic.dao.CommodityDao
import com.example.intelligent_shopping_cart.logic.db.AppDatabase
import com.example.intelligent_shopping_cart.logic.repository.CommodityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java, "my-db"
        ).build()

    @Provides
    fun provideCommodityDao(database: AppDatabase) = database.commodityDao()

    @Provides
    @Singleton
    fun provideCommodityRepository(commodityDao: CommodityDao): CommodityRepository {
        return CommodityRepository(commodityDao)
    }
}