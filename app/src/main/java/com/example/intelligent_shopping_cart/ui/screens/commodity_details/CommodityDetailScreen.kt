package com.example.intelligent_shopping_cart.ui.screens.commodity_details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Route
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.intelligent_shopping_cart.ui.components.AppScreen
import com.example.intelligent_shopping_cart.utils.LocalNavController
import com.example.intelligent_shopping_cart.view_model.CommodityUiState
import com.example.intelligent_shopping_cart.view_model.CommodityViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommodityDetailScreen(commodityViewModel: CommodityViewModel) {


    val uiState: State<CommodityUiState> = commodityViewModel.uiState.collectAsState()
    val commodity = uiState.value.selectedCommodity

    val navHostController = LocalNavController.current


    val scrollState = rememberScrollState()

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = "Commodity Detail")
            })
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = { navHostController.navigate("${AppScreen.tencentMap}/${commodity!!.id}") }) {
                Icon(Icons.Rounded.Route, contentDescription = null)
                Text(text = "route")

            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState)
        ) {
            ElevatedCard(shape = MaterialTheme.shapes.extraLarge) {
                CommodityImage(imageUrl = commodity!!.img)
                Spacer(modifier = Modifier.height(10.dp))
                CommodityInfo(commodity = commodity)
            }

            Spacer(modifier = Modifier.height(5.dp))
            UserReviews()


        }
    }

}