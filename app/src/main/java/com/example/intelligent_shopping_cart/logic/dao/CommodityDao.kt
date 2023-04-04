package com.example.intelligent_shopping_cart.logic.dao

import androidx.room.*
import com.example.intelligent_shopping_cart.model.Commodity
import kotlinx.coroutines.flow.Flow

@Dao
interface CommodityDao {
    @Query("SELECT * FROM commodity")
    fun getAllCommodities(): Flow<List<Commodity>>

    @Insert
    suspend fun insertCommodity(commodity: Commodity)

    @Insert
    suspend fun insertAll(commodities: List<Commodity>)
}