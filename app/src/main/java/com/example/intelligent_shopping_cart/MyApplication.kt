package com.example.intelligent_shopping_cart

import android.app.Application
import android.content.Context
import com.amap.api.maps.MapsInitializer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        MapsInitializer.initialize(context)
        MapsInitializer.setTerrainEnable(true)
    }


}