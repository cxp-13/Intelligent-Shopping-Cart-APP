package com.example.intelligent_shopping_cart.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.intelligent_shopping_cart.R
import com.example.intelligent_shopping_cart.model.Commodity
import com.example.intelligent_shopping_cart.model.CommodityType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

val carouselMapImagesMock = listOf(R.drawable.ava4, R.drawable.ava1, R.drawable.ava2)

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
        emptyList(),
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
    CommodityType(R.drawable.ava1, "蔬菜", vegetableList),
    CommodityType(R.drawable.ava2, "海鲜", seafoodList),
    CommodityType(R.drawable.ava3, "玩具", toyList)
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
    val carouselMapImages: List<Int> = carouselMapImagesMock,
    val commodityTypes: List<CommodityType> = commodityTypesMock,
    val isOpenSearchBox: Boolean = false,
    val searchBoxValue: String = "",
    val shoppingCartCommodityList: List<Commodity> = shoppingCartCommodityListMock
) {
    val total = commodityTypes[0].commodities.sumOf {
        it.totalPrice
    }
}

sealed class CommodityIntent() {
    object OpenSearchBox : CommodityIntent()
    data class ChangeSearchBoxValue(var searchBoxValue: String) : CommodityIntent()
}

@HiltViewModel
class CommodityViewModel @Inject constructor() : ViewModel() {
    private var _uiState = mutableStateOf(CommodityUiState())
    val uiState: State<CommodityUiState> = _uiState

    fun dispatch(intent: CommodityIntent) = reducer(_uiState.value, intent)

    private fun reducer(model: CommodityUiState, intent: CommodityIntent) {
        return when (intent) {
            CommodityIntent.OpenSearchBox -> emit(model.copy(isOpenSearchBox = !model.isOpenSearchBox))
            is CommodityIntent.ChangeSearchBoxValue -> emit(model.copy(searchBoxValue = intent.searchBoxValue))
        }
    }

    private fun emit(state: CommodityUiState) {
        _uiState.value = state
    }

    fun getCommoditiesById(typeId: String) = _uiState.value.commodityTypes.find {
        it.id == typeId
    }?.commodities ?: shoppingCartCommodityListMock

    fun getCommodityById(commodityId: String): Commodity? {
//        for (commodityType in _uiState.value.commodityTypes) {
//            for (commodity in commodityType.commodities) {
//                if (commodity.id == id) {
//                    return commodity
//                }
//            }
//        }
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