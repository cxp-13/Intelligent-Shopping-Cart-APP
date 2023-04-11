package com.example.intelligent_shopping_cart.view_model

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Navigation
import androidx.compose.material.icons.rounded.Nightlife
import androidx.compose.material.icons.rounded.Satellite
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import com.example.intelligent_shopping_cart.R
import com.example.intelligent_shopping_cart.utils.tencent.TencentLocationSource
import com.tencent.tencentmap.mapsdk.maps.MapView
import com.tencent.tencentmap.mapsdk.maps.TencentMap
import com.tencent.tencentmap.mapsdk.maps.UiSettings
import com.tencent.tencentmap.mapsdk.maps.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


enum class TencentMapLayer(val type: Int, val title: String, val icon: ImageVector) {
    NORMAL(type = TencentMap.MAP_TYPE_NORMAL, title = "normal", icon = Icons.Rounded.Navigation),
    NIGHT(type = TencentMap.MAP_TYPE_DARK, title = "night", icon = Icons.Rounded.Nightlife),
    SATELLITE(type = TencentMap.MAP_TYPE_SATELLITE, title = "navi", icon = Icons.Rounded.Satellite),
}

data class TencentMapUiState(
    var mapView: MapView? = null,
    var uiSettings: UiSettings? = null,
    val myLocationStyle: MyLocationStyle = MyLocationStyle(),
    val isShowMyLocation: Boolean = true,
    val isShowIndoorMap: Boolean = false,
    val isShow3d: Boolean = true,
    val mapType: Int = TencentMap.MAP_TYPE_NORMAL,
    var tencentMap: TencentMap? = null,
)

sealed class TencentMapIntent {
    object ToggleMyLocation : TencentMapIntent()
    data class ToggleMapType(val type: Int) : TencentMapIntent()
    object ToggleIndoorMap : TencentMapIntent()
    object Toggle3dMap : TencentMapIntent()
}


enum class FoodMark(val icon: Int, val title: String, val snippet: String, val latLng: LatLng) {
    MEAT(R.drawable.sharpicons_meat, "meat", "null", LatLng(34.383392, 108.980198)),
    MUSTARD(R.drawable.sharpicons_mustard, "mustard", "null", LatLng(34.383352, 108.980158))
}

// 构造折线点串
val latLngs: MutableList<LatLng> = mutableListOf(
    LatLng(34.383392, 108.980198),
    LatLng(34.313392, 108.920198),
    LatLng(34.387392, 108.960198)
)


@HiltViewModel
class TencentMapViewModel @Inject constructor(@ApplicationContext private val context: Context) :
    ViewModel() {

    private val _uiState: MutableStateFlow<TencentMapUiState> =
        MutableStateFlow(TencentMapUiState())

    var uiState: StateFlow<TencentMapUiState> = _uiState.asStateFlow()

    private lateinit var tencentLocationSource: TencentLocationSource

    init {
//        initVM(MyApplication.context)
        initVM(context)
    }

    private fun initVM(context: Context) {

        tencentLocationSource = TencentLocationSource(context)

        _uiState.value.apply {
            mapView = MapView(context)

            tencentMap = mapView!!.map
            uiSettings = tencentMap!!.uiSettings

            uiSettings!!.run {
                isCompassEnabled = true
            }
            tencentMap!!.run {
                //开启多窗口模式
                enableMultipleInfowindow(true)

                // 构造 PolylineOpitons
                val polylineOptions: PolylineOptions = PolylineOptions()
                    .addAll(latLngs) // 折线设置圆形线头
                    .lineCap(true) // 折线的颜色为绿色
                    .color(0xff00ff00.toInt()) // 折线宽度为25像素
                    .width(25F) // 还可以添加描边颜色
                    .borderColor(0xffff0000.toInt()) // 描边颜色的宽度，线宽还是 25 像素，不过填充的部分宽度为 `width` - 2 * `borderWidth`
                    .borderWidth(5F)
// 绘制折线
                val polyline: Polyline = addPolyline(polylineOptions)

                FoodMark.values().forEach {
                    val options = MarkerOptions(it.latLng)
                    options.title(it.title)//标注的InfoWindow的标题
                        .snippet(it.snippet)//标注的InfoWindow的内容
                        .icon(BitmapDescriptorFactory.fromResource(it.icon))
                    val marker = addMarker(options)
                    marker.isInfoWindowEnable = true
                    marker.showInfoWindow()
//                    marker.isDraggable = true
                }
                setLocationSource(tencentLocationSource)
                isTrafficEnabled = true
                uiSettings.isMyLocationButtonEnabled = true//设置默认定位按钮是否显示，非必需设置。
                isMyLocationEnabled = true // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
            }
        }
    }

    private fun update(block: TencentMapUiState.() -> TencentMapUiState) {
        this._uiState.value = this._uiState.value.block()
    }

    fun dispatch(intent: TencentMapIntent) = reducer(_uiState.value, intent)

    private fun reducer(uiState: TencentMapUiState, intent: TencentMapIntent) {
        when (intent) {
            TencentMapIntent.ToggleMyLocation -> update {
                tencentMap!!.isMyLocationEnabled = !tencentMap!!.isMyLocationEnabled
                copy(tencentMap = tencentMap, isShowMyLocation = !isShowMyLocation)
            }
            TencentMapIntent.ToggleIndoorMap -> update {
                tencentMap!!.setIndoorEnabled(!isShowIndoorMap)
                copy(tencentMap = tencentMap, isShowIndoorMap = !isShowIndoorMap)
            }
            is TencentMapIntent.ToggleMapType -> update {
                tencentMap!!.mapType = intent.type
                copy(tencentMap = tencentMap, mapType = intent.type)
            }
            TencentMapIntent.Toggle3dMap -> update {
                tencentMap!!.setBuilding3dEffectEnable(!isShow3d)
                copy(tencentMap = tencentMap, isShow3d = !isShow3d)
            }
        }
    }
}