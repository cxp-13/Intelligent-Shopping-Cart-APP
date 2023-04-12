package com.example.intelligent_shopping_cart.utils.tencent

import com.tencent.tencentmap.mapsdk.maps.TencentMap
import com.tencent.tencentmap.mapsdk.maps.model.LatLng
import com.tencent.tencentmap.mapsdk.maps.model.PolylineOptions


// 构造折线点串
val latLngsMock = listOf(
    LatLng(34.383392, 108.980198),
    LatLng(34.313392, 108.920198),
    LatLng(34.387392, 108.960198)
)

class PolylineGenerator(private val tencentMap: TencentMap) {
    fun generatePolyline(latLngs: List<LatLng> = latLngsMock) {
        val polylineOptions = PolylineOptions()
            .addAll(latLngs)
            .lineCap(true)
            .color(0xff00ff00.toInt())
            .width(25F)
            .borderColor(0xffff0000.toInt())
            .borderWidth(5F)
        val polyline = tencentMap.addPolyline(polylineOptions)
    }
}
