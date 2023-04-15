package com.example.intelligent_shopping_cart.ui.screens.map

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CameraIndoor
import androidx.compose.material.icons.rounded.Terrain
import androidx.compose.material.icons.rounded.Traffic
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.intelligent_shopping_cart.utils.requestMultiplePermission
import com.example.intelligent_shopping_cart.view_model.TencentMapIntent
import com.example.intelligent_shopping_cart.view_model.TencentMapViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.tencent.tencentmap.mapsdk.maps.MapView

@Composable
fun MapLifecycle(mapView: MapView) {

    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    DisposableEffect(context, lifecycle, mapView) {
        val mapLifecycleObserver = mapView.lifecycleObserver()

        lifecycle.addObserver(mapLifecycleObserver)

        onDispose {
            lifecycle.removeObserver(mapLifecycleObserver)
            mapView.removeAllViews()
        }
    }
}

private fun MapView.lifecycleObserver(): LifecycleEventObserver =
    LifecycleEventObserver { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> onStart()
            Lifecycle.Event.ON_RESUME -> onResume()
            Lifecycle.Event.ON_STOP -> onStop()
            Lifecycle.Event.ON_PAUSE -> onPause()
            Lifecycle.Event.ON_DESTROY -> onDestroy()
            else -> {}
        }
    }

data class MapSetting(
    val name: String,
    val status: Boolean,
    val icon: ImageVector,
    val changeStatus: () -> Unit
)

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TencentMap(tencentViewModel: TencentMapViewModel) {


    val uiState by tencentViewModel.uiState.collectAsState()

    val scaffoldState = rememberBottomSheetScaffoldState()

    val settings = listOf(
        MapSetting("traffic", uiState.isShowTraffic, Icons.Rounded.Traffic) {
            tencentViewModel.dispatch(
                TencentMapIntent.ToggleTraffic
            )
        },
        MapSetting(
            "indoor",
            uiState.isShowIndoorMap,
            Icons.Rounded.CameraIndoor
        ) {
            tencentViewModel.dispatch(TencentMapIntent.ToggleIndoorMap)
        },
        MapSetting("3d", uiState.isShow3d, Icons.Rounded.Terrain) {
            tencentViewModel.dispatch(TencentMapIntent.Toggle3dMap)
        }
    )

    val reqGPSPermission = requestMultiplePermission(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        ),
    )


    LaunchedEffect(key1 = reqGPSPermission) {
        if (!reqGPSPermission.allPermissionsGranted) {
            reqGPSPermission.launchMultiplePermissionRequest()
        }
    }

    MapLifecycle(uiState.mapView)

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            WalkPlanCard(result = uiState.route)

//            // 设置部分
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//            ) {
//                Column(modifier = Modifier.padding(16.dp)) {
//                    FilterChipRow(settings, Modifier.fillMaxWidth())
//                    Spacer(modifier = Modifier.height(16.dp))
//                    // 地图图层选择
//                    Text(text = "Map layer selection")
//                    Row(modifier = Modifier.fillMaxWidth()) {
//                        for (mapLayer in TencentMapLayer.values()) {
//                            IconButton(
//                                onClick = {
//                                    tencentViewModel.dispatch(
//                                        TencentMapIntent.ToggleMapType(
//                                            mapLayer.type
//                                        )
//                                    )
//                                },
//                                modifier = Modifier.weight(1f)
//                            ) {
//                                Icon(
//                                    imageVector = mapLayer.icon,
//                                    contentDescription = null,
//                                    tint = if (uiState.selectedMapType == mapLayer.type) Color.Blue else Color.Gray
//                                )
//                            }
//                        }
//                    }
//                }
//            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // 地图控件
            Box(Modifier.weight(1f)) {
                AndroidView(modifier = Modifier.fillMaxSize(), factory = {
                    uiState.mapView
                })
            }
        }
    }
}


