package com.example.intelligent_shopping_cart.view_model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.intelligent_shopping_cart.R
import com.example.intelligent_shopping_cart.model.Commodity
import com.example.intelligent_shopping_cart.model.CommodityType
import com.example.intelligent_shopping_cart.model.ProductCarouselItem
import com.example.intelligent_shopping_cart.ui.screens.shopping_cart.mock.appraises
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

val carouselItemList = listOf(
    ProductCarouselItem(
        id = 1,
        name = "商品1",
        imageResId = R.drawable.ava1
    ),
    ProductCarouselItem(
        id = 2,
        name = "商品2",
        imageResId = R.drawable.ava2
    ),
    ProductCarouselItem(
        id = 3,
        name = "商品3",
        imageResId = R.drawable.ava3
    ),
    ProductCarouselItem(
        id = 4,
        name = "商品4",
        imageResId = R.drawable.ava4
    ),
    ProductCarouselItem(
        id = 5,
        name = "商品5",
        imageResId = R.drawable.ava5
    )
)

val vegetableList = listOf(
    Commodity(
        "菠菜",
        R.drawable.ava1,
        3,
        "中国",
        "本地品牌",
        "500g",
        "2022-10-31 23:59:59.999",
        "新鲜可口，富含维生素",
        appraise = appraises,
        1,
        "vegetable"
    ), Commodity(
        "胡萝卜",
        R.drawable.ava2,
        2,
        "中国",
        "本地品牌",
        "500g",
        "2022-11-30 23:59:59.999",
        "口感鲜甜，营养丰富",
        emptyList(),
        1,
        "vegetable",

        ), Commodity(
        "黄瓜",
        R.drawable.ava3,
        4,
        "中国",
        "本地品牌",
        "1kg",
        "2022-12-31 23:59:59.999",
        "口感清爽，爽口解渴",
        emptyList(),
        1,
        "vegetable",

        ), Commodity(
        "黑豆",
        R.drawable.ava4,
        6,
        "中国",
        "本地品牌",
        "500g",
        "2023-01-31 23:59:59.999",
        "口感香甜，富含蛋白质",
        emptyList(),
        1,
        "vegetable",

        ), Commodity(
        "大葱",
        R.drawable.ava5,
        1,
        "中国",
        "本地品牌",
        "500g",
        "2022-11-30 23:59:59.999",
        "香味浓郁，适合烹饪",
        emptyList(),
        1,
        "vegetable",

        ), Commodity(
        "金针菇",
        R.drawable.ava6,
        5,
        "中国",
        "本地品牌",
        "200g",
        "2022-12-31 23:59:59.999",
        "口感爽脆，富含营养",
        emptyList(),
        1,
        "vegetable",

        ), Commodity(
        "海带",
        R.drawable.ava7,
        3,
        "中国",
        "本地品牌",
        "200g",
        "2023-01-31 23:59:59.999",
        "富含碘质，有益健康",
        emptyList(),
        1,
        "vegetable",

        )
)

val seafoodList = listOf(
    Commodity(
        "鲍鱼",
        R.drawable.ava1,
        50,
        "中国",
        "本地品牌",
        "1只",
        "2022-12-31 23:59:59.999",
        "鲜美可口，口感细腻",
        emptyList(),
        2,
        "seafood",
    ), Commodity(
        "扇贝",
        R.drawable.ava2,
        28,
        "中国",
        "本地品牌",
        "1kg",
        "2022-11-30 23:59:59.999",
        "肉质鲜嫩，口感爽滑",
        emptyList(),
        2,
        "seafood",
    ), Commodity(
        "大闸蟹",
        R.drawable.ava3,
        68,
        "中国",
        "本地品牌",
        "1只",
        "2022-10-31 23:59:59.999",
        "肉质鲜美，口感鲜甜",
        emptyList(),
        2,
        "seafood",
    ), Commodity(
        "三文鱼",
        R.drawable.ava4,
        38,
        "挪威",
        "进口品牌",
        "500g",
        "2023-01-31 23:59:59.999",
        "肉质鲜嫩，富含蛋白质",
        emptyList(),
        2,
        "seafood",
    ), Commodity(
        "虾仁",
        R.drawable.ava5,
        25,
        "中国",
        "本地品牌",
        "500g",
        "2022-12-31 23:59:59.999",
        "肉质鲜美，口感鲜甜",
        emptyList(),
        2,
        "seafood",
    ), Commodity(
        "生蚝",
        R.drawable.ava6,
        8,
        "中国",
        "本地品牌",
        "1只",
        "2022-11-30 23:59:59.999",
        "肉质鲜美，口感滑润",
        emptyList(),
        2,
        "seafood",
    ), Commodity(
        "龙虾",
        R.drawable.ava7,
        88,
        "加拿大",
        "进口品牌",
        "1只",
        "2023-01-31 23:59:59.999",
        "肉质鲜美，口感鲜甜",
        emptyList(),
        2,
        "seafood",
    )
)

val toyList = listOf(
    Commodity(
        "毛绒玩具熊",
        R.drawable.ava1,
        35,
        "中国",
        "本地品牌",
        "1只",
        "2022-12-31 23:59:59.999",
        "柔软可爱，适合小孩子玩耍",
        emptyList(),
        3,
        "toy",
    ), Commodity(
        "儿童自行车",
        R.drawable.ava2,
        199,
        "中国",
        "本地品牌",
        "1辆",
        "2022-11-30 23:59:59.999",
        "适合4-7岁孩子，健康运动",
        emptyList(),
        3,
        "toy",
    ), Commodity(
        "益智积木",
        R.drawable.ava3,
        45,
        "中国",
        "本地品牌",
        "100块",
        "2022-10-31 23:59:59.999",
        "锻炼孩子的动手能力和想象力",
        emptyList(),
        3,
        "toy",
    ), Commodity(
        "遥控飞机",
        R.drawable.ava4,
        149,
        "中国",
        "本地品牌",
        "1架",
        "2023-01-31 23:59:59.999",
        "可遥控飞行，适合儿童和成年人",
        emptyList(),
        3,
        "toy",
    ), Commodity(
        "玩具枪",
        R.drawable.ava5,
        29,
        "中国",
        "本地品牌",
        "1把",
        "2022-12-31 23:59:59.999",
        "安全可靠，适合儿童玩耍",
        emptyList(),
        3,
        "toy",
    ), Commodity(
        "陀螺",
        R.drawable.ava6,
        9,
        "中国",
        "本地品牌",
        "1个",
        "2022-11-30 23:59:59.999",
        "旋转稳定，适合儿童玩耍",
        emptyList(),
        3,
        "toy",
    ), Commodity(
        "双人秋千",
        R.drawable.ava7,
        99,
        "中国",
        "本地品牌",
        "1个",
        "2023-01-31 23:59:59.999",
        "适合户外活动，与小伙伴一起玩耍",
        emptyList(),
        3,
        "toy",
    )
)

val commodityTypesMock = listOf(
    CommodityType(
        R.drawable.ava1,
        "蔬菜",
        vegetableList,
        description = "蔬菜(vegetables)是指可以做菜、烹饪成为食品的一类植物或菌类，蔬菜是人们日常饮食中必不可少的食物之一。蔬菜可提供人体所必需的多种维生素和矿物质等营养物质。"
    ),
    CommodityType(
        R.drawable.ava2,
        "海鲜",
        seafoodList,
        description = "海鲜（hoisin）又称海产食物，海鲜隔夜后易产生蛋白质降解物， [7]  包括鱼类、虾类、蟹类、贝类、软体类等品类。虽然海带这类海洋生物也常是被料理成食物"
    ),
    CommodityType(
        R.drawable.ava3,
        "玩具",
        toyList,
        description = "玩具适合儿童，更适合青年和中老年人。它是打开智慧天窗的工具，让人们机智聪明。"
    )
)

val shoppingCartCommodityListMock = listOf(
    Commodity(
        name = "龙虾",
        img = R.drawable.ava1,
        price = 100,
        origin = "中国",
        brand = "自然海洋",
        specification = "500g/只",
        description = "新鲜采摘，口感鲜美",
        count = 10,
        type = "seafood"
    ),
    Commodity(
        name = "西红柿",
        img = R.drawable.ava2,
        price = 5,
        origin = "中国",
        brand = "自然农场",
        specification = "500g/份",
        description = "新鲜采摘，口感鲜美",
        count = 50,
        type = "vegetable"
    ),
    Commodity(
        name = "儿童手工拼图",
        img = R.drawable.ava3,
        price = 20,
        origin = "中国",
        brand = "儿童乐园",
        specification = "50片/盒",
        description = "促进儿童智力发展",
        count = 30,
        type = "toy"
    ),
    Commodity(
        name = "生蚝",
        img = R.drawable.ava4,
        price = 50,
        origin = "法国",
        brand = "法国生蚝",
        specification = "12个/盒",
        description = "新鲜采摘，口感鲜美",
        count = 20,
        type = "seafood"
    ),
    Commodity(
        name = "黄瓜",
        img = R.drawable.ava5,
        price = 3,
        origin = "中国",
        brand = "自然农场",
        specification = "500g/份",
        description = "新鲜采摘，口感清脆",
        count = 70,
        type = "vegetable"
    ),
    Commodity(
        name = "遥控汽车",
        img = R.drawable.ava6,
        price = 100,
        origin = "中国",
        brand = "车手天下",
        specification = "1:10比例",
        description = "适合儿童和成年人",
        count = 8,
        type = "toy"
    ),
    Commodity(
        name = "鲍鱼",
        img = R.drawable.ava7,
        price = 200,
        origin = "中国",
        brand = "福建鲍鱼",
        specification = "1只/200g",
        description = "新鲜采摘，口感鲜美",
        count = 5,
        type = "seafood"
    ),
    Commodity(
        name = "青椒",
        img = R.drawable.ava8,
        price = 2,
        origin = "中国",
        brand = "自然农场",
        specification = "250g/份",
        description = "新鲜采摘，口感清香",
        count = 100,
        type = "vegetable"
    ),
    Commodity(
        name = "毛绒玩具熊",
        img = R.drawable.ava9,
        price = 50,
        origin = "中国",
        brand = "儿童乐园",
        specification = "50cm高",
        description = "柔软舒适，适合拥抱",
        count = 20,
        type = "toy"
    )
)


data class CommodityUiState(
    // 轮播图商品列表，默认为 carouselItemList
    val carouselItems: List<ProductCarouselItem> = carouselItemList,
    // 商品分类列表，默认为 commodityTypesMock
    val commodityTypes: List<CommodityType> = commodityTypesMock,
    // 搜索框是否打开，默认为 false
//    val isOpenSearchBox: Boolean = false,
    // 搜索框的值，默认为空字符串
    var searchBoxValue: String = "",
    // 购物车中的商品列表，默认为 shoppingCartCommodityListMock
    val shoppingCartCommodityList: List<Commodity> = shoppingCartCommodityListMock,
    // 原始商品列表，默认为空列表
    val commoditiesOrigin: List<Commodity> = emptyList(),
    // 显示的商品列表，默认为空可变的 SnapshotStateList<Commodity>
    var showCommodities: SnapshotStateList<Commodity> = mutableStateListOf()
) {
    // 商品总价
    val total = commodityTypes[0].commodities.sumOf {
        it.totalPrice
    }
}

sealed class CommodityIntent() {
    //    object OpenSearchBox : CommodityIntent()
    data class ChangeSearchBoxValue(var searchBoxValue: String) : CommodityIntent()
    object SearchBtnClick : CommodityIntent()

    data class LoadCommodities(var commodityTypeId: String) : CommodityIntent()
}

@HiltViewModel
class CommodityViewModel @Inject constructor() : ViewModel() {
    private var _uiState = MutableStateFlow(CommodityUiState())
    val uiState: StateFlow<CommodityUiState> = _uiState.asStateFlow()

//    var showCommoditiesTest: SnapshotStateList<Commodity> =
//        mutableStateListOf(vegetableList[0], vegetableList[1])
//        private set

    fun dispatch(intent: CommodityIntent) = reducer(_uiState.value, intent)

    private fun reducer(model: CommodityUiState, intent: CommodityIntent) {
        return when (intent) {
//            CommodityIntent.OpenSearchBox -> emit(model.copy(isOpenSearchBox = !model.isOpenSearchBox))
            is CommodityIntent.ChangeSearchBoxValue -> changeSearchBoxValue(intent.searchBoxValue)
            CommodityIntent.SearchBtnClick -> searchBtnClick()
            is CommodityIntent.LoadCommodities -> loadCommodities(intent.commodityTypeId)
        }
    }

    private fun emit(state: CommodityUiState) {
        _uiState.value = state
    }

    private fun changeSearchBoxValue(searchBoxValue: String) {
        if (searchBoxValue.isEmpty()) {
//            _uiState.value =
//                _uiState.value.copy(showCommodities = _uiState.value.commoditiesOrigin as SnapshotStateList<Commodity>)
            _uiState.value.showCommodities.apply {
                clear()
                addAll(_uiState.value.commoditiesOrigin)
            }
        }
        _uiState.value = _uiState.value.copy(searchBoxValue = searchBoxValue)
    }

    private fun loadCommodities(typeId: String) {
        val commodities = getCommoditiesById(typeId)
        _uiState.value = _uiState.value.copy(commoditiesOrigin = commodities)
        _uiState.value.showCommodities.apply {
            clear()
            addAll(commodities)
        }
//        _uiState.value =
//            _uiState.value.copy(showCommodities = commodities as SnapshotStateList<Commodity>)
    }

    private fun searchBtnClick() {
//        showCommoditiesTest.clear()
//        showCommoditiesTest.addAll(getCommoditiesByName(_uiState.value.searchBoxValue))
//        _uiState.value =
//            _uiState.value.copy(showCommodities = getCommoditiesByName(_uiState.value.searchBoxValue))
        val commodities = getCommoditiesByName(_uiState.value.searchBoxValue)

//        _uiState.value.showCommodities = commodities.toMutableStateList()

        _uiState.value.showCommodities.apply {
            clear()
            addAll(commodities)
        }
    }

    private fun getCommoditiesByName(name: String) = _uiState.value.commoditiesOrigin.filter {
        it.name.contains(name)
    }

    private fun getCommoditiesById(typeId: String) = _uiState.value.commodityTypes.find {
        it.id == typeId
    }?.commodities ?: shoppingCartCommodityListMock

    fun getCommodityById(commodityId: String): Commodity? {
        return _uiState.value.shoppingCartCommodityList.find {
            it.id == commodityId
        }
    }

    fun getCommodityByTypeIdAndId(commodityTypeId: String, commodityId: String) =
        _uiState.value.commodityTypes.find {
            it.id == commodityTypeId
        }?.commodities?.find {
            it.id == commodityId
        }


}