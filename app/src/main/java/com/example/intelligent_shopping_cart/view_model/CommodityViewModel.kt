package com.example.intelligent_shopping_cart.view_model

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intelligent_shopping_cart.logic.repository.CommodityRepository
import com.example.intelligent_shopping_cart.model.Commodity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CommodityUiState(
    // 搜索框的值，默认为空字符串
    var searchBoxValue: String = "",
    // 全部商品
    var commodities: List<Commodity> = emptyList(),

    val selectedType: String = "",

    val selectedCommodity: Commodity? = null

) {
    // 轮播图商品列表，默认为 carouselItemList
    var carouselItems: List<Commodity> = emptyList()

    // 购物车中的商品列表
    var placeCommodities: List<Commodity> = emptyList()

    // 商品总价
    var total: Int = 0

    var commodityTypeMap: Map<String, List<Commodity>> = emptyMap()

    var displayCommodities: List<Commodity> = emptyList()
}

sealed class CommodityIntent() {
    data class ChangeSearchBoxValue(var searchBoxValue: String) : CommodityIntent()
    data class SelectType(val type: String) : CommodityIntent()

    data class SelectCommodity(val id: Int) : CommodityIntent()
}

@HiltViewModel
class CommodityViewModel @Inject constructor(val commodityRepository: CommodityRepository) :
    ViewModel() {
    private var _uiState = mutableStateOf(CommodityUiState())
    val uiState: State<CommodityUiState> = _uiState

    init {
        initUiState()
    }

    fun initUiState() {
        viewModelScope.launch {
            commodityRepository.getAllCommodities().collect { commodities ->
                Log.d("cxp", "collect: ")
                _uiState.value.commodities = commodities
                _uiState.value.carouselItems = _uiState.value.commodities.subList(0, 5)
                _uiState.value.placeCommodities = _uiState.value.commodities.subList(0, 5)
                _uiState.value.commodityTypeMap = _uiState.value.commodities.groupBy { it.type }
                _uiState.value.total = _uiState.value.placeCommodities.sumOf {
                    it.price * it.count
                }
                _uiState.value.displayCommodities =
                    _uiState.value.commodityTypeMap[_uiState.value.selectedType]?.filter {
                        _uiState.value.searchBoxValue.isEmpty() || it.name.contains(_uiState.value.searchBoxValue)
                    } ?: emptyList()

            }
            Log.d("cxp", "viewModelScope.launch: ${_uiState.value}")
        }
    }

    private fun CommodityUiState.update(block: CommodityUiState.() -> CommodityUiState) {
        _uiState.value = this@update.block()
    }

    fun dispatch(intent: CommodityIntent) = reducer(_uiState.value, intent)
    private fun reducer(uiState: CommodityUiState, intent: CommodityIntent) {
        return when (intent) {
            is CommodityIntent.ChangeSearchBoxValue -> uiState.update {
                copy(searchBoxValue = intent.searchBoxValue)
            }
            is CommodityIntent.SelectType -> uiState.update {
                copy(selectedType = intent.type)
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