package com.example.intelligent_shopping_cart.model

import com.tencent.tencentmap.mapsdk.maps.model.LatLng

data class Mark(
    val icon: Int,
    val title: String,
    val snippet: String,
    val latLng: LatLng = LatLng()
)