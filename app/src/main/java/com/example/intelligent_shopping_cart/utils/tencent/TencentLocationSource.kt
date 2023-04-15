package com.example.intelligent_shopping_cart.utils.tencent

import android.content.Context
import android.location.Location
import android.os.Looper
import android.widget.Toast
import com.tencent.map.geolocation.TencentLocation
import com.tencent.map.geolocation.TencentLocationListener
import com.tencent.map.geolocation.TencentLocationManager
import com.tencent.map.geolocation.TencentLocationRequest
import com.tencent.tencentmap.mapsdk.maps.LocationSource

class TencentLocationSource(val context: Context) :
    LocationSource {

    private var locationChangedListener: LocationSource.OnLocationChangedListener? = null


    private var tencentLocationListener = object : TencentLocationListener {
        override fun onLocationChanged(tencentLocation: TencentLocation, i: Int, s: String?) {
            //其中 locationChangeListener 为 LocationSource.active 返回给用户的位置监听器
            //用户通过这个监听器就可以设置地图的定位点位置
            if (i === TencentLocation.ERROR_OK && locationChangedListener != null) {
                val location: Location = Location(tencentLocation.provider)
                //设置经纬度
                location.latitude = tencentLocation.latitude
                location.longitude = tencentLocation.longitude
                //设置精度，这个值会被设置为定位点上表示精度的圆形半径
                location.accuracy = tencentLocation.accuracy
                //设置定位标的旋转角度，注意 tencentLocation.getBearing() 只有在 gps 时才有可能获取
                location.bearing = tencentLocation.bearing
                //将位置信息返回给地图
                locationChangedListener!!.onLocationChanged(location)
            }
        }

        override fun onStatusUpdate(p0: String?, p1: Int, p2: String?) {
        }
    }

    private var locationRequest: TencentLocationRequest? = TencentLocationRequest.create()

    var locationManager: TencentLocationManager? = TencentLocationManager.getInstance(context)

    init {
        //用于访问腾讯定位服务的类, 周期性向客户端提供位置更新
        //创建定位请求
        //设置定位周期（位置监听器回调周期）为3s
        locationRequest!!.interval = 3000
    }


    override fun activate(onLocationChangedListener: LocationSource.OnLocationChangedListener) {
        //这里我们将地图返回的位置监听保存为当前 Activity 的成员变量
        locationChangedListener = onLocationChangedListener
        //开启定位
        val err = locationManager!!.requestLocationUpdates(
            locationRequest, tencentLocationListener, Looper.myLooper()
        )
        when (err) {
            1 -> Toast.makeText(
                context,
                "设备缺少使用腾讯定位服务需要的基本条件",
                Toast.LENGTH_SHORT
            ).show()
            2 -> Toast.makeText(
                context,
                "manifest 中配置的 key 不正确", Toast.LENGTH_SHORT
            ).show()
            3 -> Toast.makeText(
                context,
                "自动加载libtencentloc.so失败", Toast.LENGTH_SHORT
            ).show()
            else -> {}
        }
    }

    override fun deactivate() {
        //当不需要展示定位点时，需要停止定位并释放相关资源
        locationManager?.removeUpdates(tencentLocationListener)
        locationManager = null
        locationRequest = null
        locationChangedListener = null
    }
}