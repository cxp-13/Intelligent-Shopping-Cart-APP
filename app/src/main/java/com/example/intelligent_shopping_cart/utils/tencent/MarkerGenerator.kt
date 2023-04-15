package com.example.intelligent_shopping_cart.utils.tencent

import com.example.intelligent_shopping_cart.R
import com.example.intelligent_shopping_cart.logic.utils.CURRENT_LOCATION
import com.example.intelligent_shopping_cart.model.Mark
import com.tencent.tencentmap.mapsdk.maps.TencentMap
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptorFactory
import com.tencent.tencentmap.mapsdk.maps.model.LatLng
import com.tencent.tencentmap.mapsdk.maps.model.Marker
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions


val supermarketMarksList = listOf(
    Mark(R.drawable.sharpicons_meat, "Grocery", "Grocery store"),
    Mark(R.drawable.sharpicons_mustard, "Beverage", "Beverage store"),
    Mark(R.drawable.sharpicons_meat, "Household", "Household store"),
    Mark(R.drawable.sharpicons_mustard, "Personal Care", "Personal care store"),
    Mark(R.drawable.sharpicons_meat, "Pet Supplies", "Pet supplies store")
)

class MarkerGenerator(var tencentMap: TencentMap) {
    // 生成随机数量的标记点
    fun generateMarkers(
        supermarketMarks: List<Mark> = supermarketMarksList
    ) {
        val currentLocation = getCurrentLocation() // 获取当前位置经纬度
        supermarketMarks.forEach { mark ->
            val randomLatLng = generateRandomLatLng(currentLocation) // 生成随机经纬度
            val curMark = mark.copy(latLng = randomLatLng)
            addMarker(curMark) // 在地图上添加标记点
        }
    }

    // 获取当前位置经纬度
    private fun getCurrentLocation(): LatLng {
//        val myLocation = tencentMap.myLocation
//        return LatLng(myLocation.latitude, myLocation.longitude)
        return CURRENT_LOCATION
    }

    // 生成随机经纬度
    private fun generateRandomLatLng(currentLocation: LatLng): LatLng {
        val lat = currentLocation.latitude + (Math.random() - 0.5) * 0.1 // 在当前位置的纬度范围内生成随机纬度
        val lng = currentLocation.longitude + (Math.random() - 0.5) * 0.1 // 在当前位置的经度范围内生成随机经度
        return LatLng(lat, lng)
    }


    // 在地图上添加标记点
    private fun addMarker(mark: Mark) {
        val markerOptions = MarkerOptions(mark.latLng)
            .title(mark.title) // 标记点标题
            .snippet(mark.snippet) // 标记点描述
            .icon(BitmapDescriptorFactory.fromResource(mark.icon))
        val marker: Marker = tencentMap.addMarker(markerOptions) // 添加标记点到地图上
        marker.isInfoWindowEnable = true
        marker.showInfoWindow()
        // 设置标记点的图标、颜色等其他属性
    }
}
