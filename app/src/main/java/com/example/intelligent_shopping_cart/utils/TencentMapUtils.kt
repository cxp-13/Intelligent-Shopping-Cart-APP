package com.example.intelligent_shopping_cart.utils

import com.tencent.map.geolocation.TencentLocationManager
import com.tencent.tencentmap.mapsdk.maps.TencentMapInitializer

object TencentMapUtils {
    /**
     * 设置用户是否同意隐私协议
     * 需要在初始化地图之前完成，传入true后才能正常使用地图功能
     * @param isAgree 是否同意隐私协议
     */
    fun setAgreePrivacy() {
        TencentLocationManager.setUserAgreePrivacy(true)
        TencentMapInitializer.setAgreePrivacy(true)
    }

}