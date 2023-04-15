package com.example.intelligent_shopping_cart.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intelligent_shopping_cart.logic.repository.CommodityRepository
import com.example.intelligent_shopping_cart.logic.utils.CommodityFactory
import com.example.intelligent_shopping_cart.model.Commodity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CommodityUiState(
    // 搜索框的值，默认为空字符串
    var searchBoxValue: String = "",
    // 全部商品
    var commodities: List<Commodity> = emptyList(),

    val selectedType: String = "",

    val selectedCommodity: Commodity? = null,

    // 轮播图商品列表，默认为 carouselItemList
    var carouselItems: List<Commodity> = emptyList(),

    // 购物车中的商品列表
    var placeCommodities: List<Commodity> = emptyList(),

    // 商品总价
    var total: Int = 0,

    var commodityTypeMap: Map<String, List<Commodity>> = emptyMap(),

    var commoditiesForType: List<Commodity> = emptyList(),

    var displayCommodities: List<Commodity> = emptyList()
) {

}

sealed class CommodityIntent() {
    data class ChangeSearchBoxValue(var searchBoxValue: String) : CommodityIntent()
    data class SelectType(val type: String) : CommodityIntent()
    data class SelectCommodity(val id: Int) : CommodityIntent()
}

@HiltViewModel
class CommodityViewModel @Inject constructor(private val commodityRepository: CommodityRepository) :
    ViewModel() {
    private var _uiState = MutableStateFlow(CommodityUiState())
    val uiState: StateFlow<CommodityUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            // 创建一个包含 10 个虚拟商品的商品列表
            val commodityList = CommodityFactory.createDummyCommodities(10)

            // 将商品列表插入到数据库中
            commodityRepository.insertAll(commodityList)

            // 从数据库中获取所有商品，并在获取到商品信息后更新 UI 状态
            commodityRepository.getAllCommodities().collect { commoditiesValue ->
                _uiState.value.update {
                    // 检查商品列表的长度是否足够
                    val subListSize = minOf(commoditiesValue.size, 5)
                    copy(
                        commodities = commoditiesValue,
                        carouselItems = commoditiesValue.subList(0, subListSize),
                        placeCommodities = commoditiesValue.subList(0, subListSize),
                        commodityTypeMap = commoditiesValue.groupBy { it.type },
                        total = placeCommodities.sumOf { it.price * it.count }
                    )
                }
            }
        }

    }

    private fun CommodityUiState.update(block: CommodityUiState.() -> CommodityUiState) {
        _uiState.value = this@update.block()
    }

    fun dispatch(intent: CommodityIntent) = reducer(_uiState.value, intent)
    private fun reducer(uiState: CommodityUiState, intent: CommodityIntent) {
        return when (intent) {
            is CommodityIntent.ChangeSearchBoxValue -> uiState.update {
                Log.d("reducer", "reducer: ${intent.searchBoxValue}")
//                copy(searchBoxValue = )
                copy(
                    searchBoxValue = intent.searchBoxValue,
                    displayCommodities = commoditiesForType.filter {
                        intent.searchBoxValue.isEmpty() || it.name.contains(intent.searchBoxValue)
                    })
            }
            is CommodityIntent.SelectType -> uiState.update {
                copy(
                    selectedType = intent.type,
                    commoditiesForType = commodityTypeMap[intent.type]!!,
                    displayCommodities = commodityTypeMap[intent.type]!!
                )
            }
            is CommodityIntent.SelectCommodity -> uiState.update {
                val commodity = commodities.find {
                    it.id == intent.id
                }
                copy(selectedCommodity = commodity!!)
            }
        }
    }
}