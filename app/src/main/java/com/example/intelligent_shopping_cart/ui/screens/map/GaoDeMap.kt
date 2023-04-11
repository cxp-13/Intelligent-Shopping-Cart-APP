package com.example.intelligent_shopping_cart.ui.screens.map

import android.Manifest
import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.amap.api.maps.MapView
import com.example.intelligent_shopping_cart.utils.requestMultiplePermission
import com.example.intelligent_shopping_cart.view_model.GaoDeMapIntent
import com.example.intelligent_shopping_cart.view_model.GaoDeMapLayer
import com.example.intelligent_shopping_cart.view_model.GaoDeMapViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@Composable
fun MapLifecycle(mapView: MapView) {
    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    DisposableEffect(context, lifecycle, mapView) {
        val mapLifecycleObserver = mapView.lifecycleObserver()

        lifecycle.addObserver(mapLifecycleObserver)

        onDispose {
            lifecycle.removeObserver(mapLifecycleObserver)
            // fix memory leak
            mapView.onDestroy()
            mapView.removeAllViews()
        }
    }
}

private fun MapView.lifecycleObserver(): LifecycleEventObserver =
    LifecycleEventObserver { _, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                this.onCreate(Bundle())
            }
            Lifecycle.Event.ON_RESUME -> {
                this.onResume()
            }
            Lifecycle.Event.ON_PAUSE -> this.onPause()
            Lifecycle.Event.ON_DESTROY -> {
                // handled in onDispose
            }
            else -> { /* ignore */
            }
        }
    }


@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun GaoDeMap(gaoDeViewModel: GaoDeMapViewModel) {

    val uiState by gaoDeViewModel.uiState.collectAsState()

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

    MapLifecycle(uiState.mapView!!)

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text(text = "地图室内导航") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            // 地图控件
            Box(Modifier.weight(1f)) {
                AndroidView(modifier = Modifier.fillMaxSize(), factory = {
                    uiState.mapView!!
                })
            }
            // 设置部分
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // 是否显示蓝色点开关
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "是否显示蓝色点")
                        Switch(
                            checked = uiState.isShowBluePoint,
                            onCheckedChange = { gaoDeViewModel.dispatch(GaoDeMapIntent.ToggleBluePoint) }
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    // 地图图层选择
                    Text(text = "地图图层选择")
                    Row(modifier = Modifier.fillMaxWidth()) {
                        for (mapLayer in GaoDeMapLayer.values()) {
                            IconButton(
                                onClick = {
                                    gaoDeViewModel.dispatch(
                                        GaoDeMapIntent.ToggleMapType(
                                            mapLayer.type
                                        )
                                    )
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    imageVector = mapLayer.icon,
                                    contentDescription = null,
                                    tint = if (uiState.mapType == mapLayer.type) Color.Blue else Color.Gray
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    // 是否显示室内图开关
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "是否显示室内图")
                        Switch(
                            checked = uiState.isShowIndoorMap,
                            onCheckedChange = {
                                gaoDeViewModel.dispatch(
                                    GaoDeMapIntent.ToggleIndoorMap
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}