package com.example.intelligent_shopping_cart.logic.repository

import com.example.intelligent_shopping_cart.logic.dao.CommodityDao
import com.example.intelligent_shopping_cart.model.Commodity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CommodityRepository @Inject constructor(
    private val commodityDao: CommodityDao
) {
    fun getAllCommodities(): Flow<List<Commodity>> = commodityDao.getAllCommodities()

    suspend fun insertCommodity(commodity: Commodity) = commodityDao.insertCommodity(commodity)

    suspend fun insertAll(commodities: List<Commodity>) = commodityDao.insertAll(commodities)
}