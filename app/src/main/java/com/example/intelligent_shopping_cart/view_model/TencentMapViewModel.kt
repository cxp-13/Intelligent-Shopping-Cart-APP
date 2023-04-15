package com.example.intelligent_shopping_cart.view_model

import android.content.Context
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Navigation
import androidx.compose.material.icons.rounded.Nightlife
import androidx.compose.material.icons.rounded.Satellite
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intelligent_shopping_cart.logic.repository.CommodityRepository
import com.example.intelligent_shopping_cart.model.Commodity
import com.example.intelligent_shopping_cart.utils.tencent.TencentLocationSource
import com.example.intelligent_shopping_cart.utils.tencent.WalkPlanningGenerator
import com.tencent.lbssearch.`object`.result.WalkingResultObject.Route
import com.tencent.tencentmap.mapsdk.maps.MapView
import com.tencent.tencentmap.mapsdk.maps.TencentMap
import com.tencent.tencentmap.mapsdk.maps.UiSettings
import com.tencent.tencentmap.mapsdk.maps.model.LatLng
import com.tencent.tencentmap.mapsdk.maps.model.MyLocationStyle
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


enum class TencentMapLayer(val type: Int, val title: String, val icon: ImageVector) {
    NORMAL(type = TencentMap.MAP_TYPE_NORMAL, title = "normal", icon = Icons.Rounded.Navigation),
    NIGHT(type = TencentMap.MAP_TYPE_DARK, title = "night", icon = Icons.Rounded.Nightlife),
    SATELLITE(type = TencentMap.MAP_TYPE_SATELLITE, title = "navi", icon = Icons.Rounded.Satellite),
}

data class TencentMapUiState(
    val mapView: MapView,
    val uiSettings: UiSettings?,
    val isShowIndoorMap: Boolean,
    val isShow3d: Boolean,
    val isShowTraffic: Boolean,
    val selectedMapType: Int,
    val tencentMap: TencentMap?,
    val selectedCommodity: Commodity?,
    val route: Route?
)

sealed class TencentMapIntent {
    object ToggleTraffic : TencentMapIntent()
    data class ToggleMapType(val type: Int) : TencentMapIntent()
    object ToggleIndoorMap : TencentMapIntent()
    object Toggle3dMap : TencentMapIntent()
    data class LoadingWalkPlan(val commodityId: Int) : TencentMapIntent()
}

@HiltViewModel
class TencentMapViewModel @Inject constructor(
    @ApplicationContext context: Context,
    val commodityRepository: CommodityRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<TencentMapUiState> =
        MutableStateFlow(
            TencentMapUiState(
                mapView = MapView(context),
                uiSettings = null,
                isShowIndoorMap = false,
                isShow3d = false,
                isShowTraffic = false,
                selectedMapType = TencentMap.MAP_TYPE_NORMAL,
                tencentMap = null,
                selectedCommodity = null,
                route = null
            )
        )

    var uiState: StateFlow<TencentMapUiState> = _uiState.asStateFlow()

    private val event: MutableSharedFlow<TencentMapIntent> = MutableSharedFlow()

    private var tencentLocationSource: TencentLocationSource = TencentLocationSource(context)

    init {
        update {
            copy(tencentMap = mapView.map.apply {
                setMyLocationStyle(MyLocationStyle().myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER))
                Log.d("checkTime", "init")
                setLocationSource(tencentLocationSource)
                uiSettings.isMyLocationButtonEnabled = true//设置默认定位按钮是否显示，非必需设置。
                isMyLocationEnabled = true // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

                //开启多窗口模式
                enableMultipleInfowindow(true)

            })
        }

        update {
            copy(uiSettings = tencentMap!!.uiSettings.apply {
                isCompassEnabled = true
            })
        }

        viewModelScope.launch(Dispatchers.Main) {

//            loadCommodity()

            event.collect {
                reducer(_uiState.value, intent = it)
            }
        }
    }

    private fun update(block: TencentMapUiState.() -> TencentMapUiState) {
        this._uiState.value = this._uiState.value.block()
    }

    fun dispatch(intent: TencentMapIntent) {
        viewModelScope.launch(Dispatchers.Main) {
            event.emit(intent)
//            event.send(intent)
        }
    }

    private suspend fun loadCommodity(commodityId: Int) {
        commodityRepository.getCommodityById(commodityId).collect {
            update {
                copy(selectedCommodity = it)
            }
            val tencentMap = _uiState.value.tencentMap

            tencentMap!!.setOnMyLocationChangeListener { location ->

                val fromPoint = LatLng(location.latitude, location.longitude)

                val toPoint =
                    LatLng(
                        _uiState.value.selectedCommodity!!.latitude,
                        _uiState.value.selectedCommodity!!.longitude
                    )

                WalkPlanningGenerator(_uiState.value.tencentMap!!).getWalkingRoute(
                    fromPoint,
                    toPoint
                ) {
                    update {
                        copy(route = it)
                    }
                }
            }
        }
    }

    // 更新 Tencent 地图 UI 状态的 reducer 函数
    private fun reducer(uiState: TencentMapUiState, intent: TencentMapIntent) {
        when (intent) {
            TencentMapIntent.ToggleTraffic -> update {
                // 切换交通流量显示
                tencentMap!!.isTrafficEnabled = !tencentMap!!.isTrafficEnabled
                // 更新状态中的 isShowTraffic 属性
                copy(isShowTraffic = !isShowTraffic)
            }
            TencentMapIntent.ToggleIndoorMap -> update {
                // 切换室内地图显示
                tencentMap!!.setIndoorEnabled(!isShowIndoorMap)
                // 更新状态中的 isShowIndoorMap 属性
                copy(isShowIndoorMap = !isShowIndoorMap)
            }
            is TencentMapIntent.ToggleMapType -> update {
                // 切换地图类型
                tencentMap!!.mapType = intent.type
                // 更新状态中的 selectedMapType 属性
                copy(selectedMapType = intent.type)
            }
            TencentMapIntent.Toggle3dMap -> update {
                // 切换 3D 地图显示
                tencentMap!!.setBuilding3dEffectEnable(!isShow3d)
                // 更新状态中的 isShow3d 属性
                copy(isShow3d = !isShow3d)
            }
            is TencentMapIntent.LoadingWalkPlan -> {
                viewModelScope.launch {
                    loadCommodity(intent.commodityId)
                }
            }
        }
    }

}