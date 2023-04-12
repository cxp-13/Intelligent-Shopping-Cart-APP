package com.example.intelligent_shopping_cart.view_model

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Navigation
import androidx.compose.material.icons.rounded.Nightlife
import androidx.compose.material.icons.rounded.Satellite
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intelligent_shopping_cart.utils.tencent.TencentLocationSource
import com.tencent.tencentmap.mapsdk.maps.MapView
import com.tencent.tencentmap.mapsdk.maps.TencentMap
import com.tencent.tencentmap.mapsdk.maps.UiSettings
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
    val mapView: MapView?,
    var uiSettings: UiSettings?,
    val isShowIndoorMap: Boolean,
    val isShow3d: Boolean,
    val isShowTraffic: Boolean,
    val selectedMapType: Int,
    var tencentMap: TencentMap?,
)

sealed class TencentMapIntent {
    object ToggleTraffic : TencentMapIntent()
    data class ToggleMapType(val type: Int) : TencentMapIntent()
    object ToggleIndoorMap : TencentMapIntent()
    object Toggle3dMap : TencentMapIntent()
}

@HiltViewModel
class TencentMapViewModel @Inject constructor(@ApplicationContext private val context: Context) :
    ViewModel() {

    private val _uiState: MutableStateFlow<TencentMapUiState> =
        MutableStateFlow(
            TencentMapUiState(
                mapView = MapView(context),
                uiSettings = null,
                isShowIndoorMap = false,
                isShow3d = false,
                isShowTraffic = false,
                selectedMapType = TencentMap.MAP_TYPE_NORMAL,
                tencentMap = null
            )
        )

    var uiState: StateFlow<TencentMapUiState> = _uiState.asStateFlow()


//    private val _effect: Channel<String> = Channel()
//    val effect = _effect

    private val event: MutableSharedFlow<TencentMapIntent> = MutableSharedFlow()

    private var tencentLocationSource: TencentLocationSource = TencentLocationSource(context)

    init {
        _uiState.value.apply {
            tencentMap = mapView!!.map
            uiSettings = tencentMap!!.uiSettings

            uiSettings!!.run {
                isCompassEnabled = true
            }

            tencentMap!!.run {
                setLocationSource(tencentLocationSource)
                uiSettings.isMyLocationButtonEnabled = true//设置默认定位按钮是否显示，非必需设置。
                isMyLocationEnabled = true // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
                //开启多窗口模式
                enableMultipleInfowindow(true)
            }
//            MarkerGenerator(tencentMap!!).generateMarkers()
//            PolylineGenerator(tencentMap!!).generatePolyline()
        }

        viewModelScope.launch(Dispatchers.Main) {
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
        }
    }

    private fun reducer(uiState: TencentMapUiState, intent: TencentMapIntent) {
        when (intent) {
            TencentMapIntent.ToggleTraffic -> update {
                tencentMap!!.isTrafficEnabled = !tencentMap!!.isTrafficEnabled
                copy(isShowTraffic = !isShowTraffic)
            }
            TencentMapIntent.ToggleIndoorMap -> update {
                tencentMap!!.setIndoorEnabled(!isShowIndoorMap)
                copy(isShowIndoorMap = !isShowIndoorMap)
            }
            is TencentMapIntent.ToggleMapType -> update {
                tencentMap!!.mapType = intent.type
                copy(selectedMapType = intent.type)
            }
            TencentMapIntent.Toggle3dMap -> update {
                tencentMap!!.setBuilding3dEffectEnable(!isShow3d)
                copy(isShow3d = !isShow3d)
            }
        }
    }
}