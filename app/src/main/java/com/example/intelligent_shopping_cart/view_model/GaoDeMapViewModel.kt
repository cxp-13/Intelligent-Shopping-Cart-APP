package com.example.intelligent_shopping_cart.view_model

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import com.amap.api.maps.AMap
import com.amap.api.maps.MapView
import com.amap.api.maps.model.MyLocationStyle
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


enum class GaoDeMapLayer(val type: Int, val title: String, val icon: ImageVector) {
    NAVI(type = AMap.MAP_TYPE_NAVI, title = "navi", icon = Icons.Rounded.Navigation),
    NORMAL(type = AMap.MAP_TYPE_NORMAL, title = "normal", icon = Icons.Rounded.AutoFixNormal),
    NIGHT(type = AMap.MAP_TYPE_NIGHT, title = "night", icon = Icons.Rounded.Nightlife),
    SATELLITE(type = AMap.MAP_TYPE_SATELLITE, title = "navi", icon = Icons.Rounded.Satellite),
    BUS(type = AMap.MAP_TYPE_BUS, title = "bus", icon = Icons.Rounded.BusAlert), ;
}

data class GaoDeMapUiState(
    var mapView: MapView? = null,
    val myLocationStyle: MyLocationStyle = MyLocationStyle(),
    val isShowBluePoint: Boolean = true,
    val isShowIndoorMap: Boolean = false,
    val mapType: Int = AMap.MAP_TYPE_NORMAL,
    var aMap: AMap? = null
)

sealed class GaoDeMapIntent {
    object ToggleBluePoint : GaoDeMapIntent()
    data class ToggleMapType(val type: Int) : GaoDeMapIntent()
    object ToggleIndoorMap : GaoDeMapIntent()
}

@HiltViewModel
class GaoDeMapViewModel @Inject constructor(@ApplicationContext private val context: Context) :
    ViewModel() {

    private val _uiState: MutableStateFlow<GaoDeMapUiState> =
        MutableStateFlow(GaoDeMapUiState())

    var uiState: StateFlow<GaoDeMapUiState> = _uiState.asStateFlow()

    init {
//        initVM(MyApplication.context)
        initVM(context)
    }

    private fun initVM(context: Context) {
        _uiState.value.apply {
            mapView = MapView(context)
            aMap = mapView!!.map
            aMap!!.run {
                isTrafficEnabled = true
                uiSettings.isMyLocationButtonEnabled = true//设置默认定位按钮是否显示，非必需设置。
                isMyLocationEnabled = true // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
            }
        }
    }

    private fun update(block: GaoDeMapUiState.() -> GaoDeMapUiState) {
        this._uiState.value = this._uiState.value.block()
    }

    fun dispatch(intent: GaoDeMapIntent) = reducer(_uiState.value, intent)

    private fun reducer(uiState: GaoDeMapUiState, intent: GaoDeMapIntent) {
        when (intent) {
            GaoDeMapIntent.ToggleBluePoint -> update {
                aMap!!.isMyLocationEnabled = !aMap!!.isMyLocationEnabled
                copy(aMap = aMap, isShowBluePoint = !isShowBluePoint)
            }
            GaoDeMapIntent.ToggleIndoorMap -> update {
                aMap!!.showIndoorMap(!isShowIndoorMap)
                copy(aMap = aMap, isShowIndoorMap = !isShowIndoorMap)
            }
            is GaoDeMapIntent.ToggleMapType -> update {
                aMap!!.mapType = intent.type
                copy(aMap = aMap, mapType = intent.type)
            }
        }
    }
}