package com.example.intelligent_shopping_cart.logic.dao

import androidx.room.*
import com.example.intelligent_shopping_cart.model.Commodity
import kotlinx.coroutines.flow.Flow

@Dao
interface CommodityDao {
    @Query("SELECT * FROM commodity")
    fun getAllCommodities(): Flow<List<Commodity>>

    @Query("SELECT * FROM commodity WHERE id = :id")
    fun getCommodityById(id: Int): Flow<Commodity?>

    @Insert
    suspend fun insertCommodity(commodity: Commodity)

    @Insert
    suspend fun insertAll(commodities: List<Commodity>)
}