package com.example.intelligent_shopping_cart.logic.repository

import com.example.intelligent_shopping_cart.logic.dao.CommodityDao
import com.example.intelligent_shopping_cart.model.Commodity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CommodityRepository @Inject constructor(
    private val commodityDao: CommodityDao
) {
    suspend fun getAllCommodities(): Flow<List<Commodity>> =
        withContext(Dispatchers.IO) {
            commodityDao.getAllCommodities()
        }

    // 根据 id 查找单个 Commodity 对象
    suspend fun getCommodityById(id: Int): Flow<Commodity?> = withContext(Dispatchers.IO) {
        commodityDao.getCommodityById(id)
    }

    suspend fun insertCommodity(commodity: Commodity) = withContext(Dispatchers.IO) {
        commodityDao.insertCommodity(commodity)
    }

    suspend fun insertAll(commodities: List<Commodity>) = withContext(Dispatchers.IO) {
        commodityDao.insertAll(commodities)
    }
}