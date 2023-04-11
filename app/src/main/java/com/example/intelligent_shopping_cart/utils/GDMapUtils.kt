package com.example.intelligent_shopping_cart.utils

import android.content.Context
import com.amap.api.maps.MapsInitializer

object GDMapUtils {
    /**
     * 更新地图的隐私合规，【不调用地图无法正常显示】要先调用它
     */
    fun updateMapViewPrivacy(context: Context) {
        MapsInitializer.updatePrivacyShow(context.applicationContext, true, true)
        MapsInitializer.updatePrivacyAgree(context.applicationContext, true)
    }
}