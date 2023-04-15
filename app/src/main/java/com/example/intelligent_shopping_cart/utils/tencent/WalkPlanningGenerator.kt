package com.example.intelligent_shopping_cart.utils.tencent

import android.util.Log
import com.example.intelligent_shopping_cart.MyApplication
import com.tencent.lbssearch.TencentSearch
import com.tencent.lbssearch.`object`.param.WalkingParam
import com.tencent.lbssearch.`object`.result.WalkingResultObject
import com.tencent.lbssearch.`object`.result.WalkingResultObject.Route
import com.tencent.map.tools.net.http.HttpResponseListener
import com.tencent.tencentmap.mapsdk.maps.TencentMap
import com.tencent.tencentmap.mapsdk.maps.model.LatLng
import com.tencent.tencentmap.mapsdk.maps.model.PolylineOptions


class WalkPlanningGenerator(private val tencentMap: TencentMap) {
//    // 起点坐标
//    private val fromPoint: LatLng = LatLng(34.383392, 108.980198)
//    // 终点坐标
//    private val toPoint: LatLng = LatLng(34.313392, 108.900198)
    /**
     * 获取步行导航规划
     */
    fun getWalkingRoute(
        fromPoint: LatLng,
        toPoint: LatLng,
        function: (Route) -> Unit,
    ) {
        val walkingParam = WalkingParam().from(fromPoint).to(toPoint)
        val tencentSearch = TencentSearch(MyApplication.context)
        Log.i("WalkPlanningGenerator", "checkParams:" + walkingParam.checkParams())
        tencentSearch.getRoutePlan(
            walkingParam,
            object : HttpResponseListener<WalkingResultObject?> {
                override fun onSuccess(statusCode: Int, walkingResultObject: WalkingResultObject?) {
                    if (walkingResultObject == null) {
                        Log.i("WalkPlanningGenerator", "baseObject为空")
                        return
                    }
                    function(walkingResultObject.result.routes[0])
                    showWalkingRoute(walkingResultObject)
                    Log.i("WalkPlanningGenerator", "message:" + walkingResultObject.message)
                }

                override fun onFailure(
                    statusCode: Int,
                    responseString: String,
                    throwable: Throwable
                ) {
                    Log.i("WalkPlanningGenerator:", "$statusCode  $responseString")
                }
            })
    }

    private fun showWalkingRoute(walkingResultObject: WalkingResultObject) {
        tencentMap.clearAllOverlays()
        if (walkingResultObject.result != null && walkingResultObject.result.routes != null && walkingResultObject.result.routes.size > 0) {
            for (i in walkingResultObject.result.routes.indices) {
                val result = walkingResultObject.result.routes[i]
                tencentMap.addPolyline(
                    PolylineOptions().addAll(result.polyline).color(i + 1).width(20f)
                )
                Log.i(
                    "WalkPlanningGenerator",
                    "distance:" + result.distance + " duration:" + result.duration
                            + " mode:" + result.mode + " direction:" + result.direction
                )
                for (step in result.steps) {
                    Log.i(
                        "WalkPlanningGenerator",
                        "step:" + step.road_name + " " + step.distance + " "
                                + step.instruction + " " + step.act_desc + " " + step.dir_desc
                    )
                }
//                tencentMap.moveCamera(
//                    CameraUpdateFactory.newLatLngBounds(
//                        LatLngBounds.builder()
//                            .include(result.polyline).build(), 100
//                    )
//                )
            }
        } else {
            Log.i("WalkPlanningGenerator", "路线结果为空")
        }
    }
}