package com.example.intelligent_shopping_cart

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.intelligent_shopping_cart.ui.components.AppScaffold
import com.example.intelligent_shopping_cart.ui.components.AppScreen
import com.example.intelligent_shopping_cart.ui.screens.commodity_details.CommodityDetail
import com.example.intelligent_shopping_cart.ui.screens.commodity_list.CommodityList
import com.example.intelligent_shopping_cart.ui.screens.login.Login
import com.example.intelligent_shopping_cart.ui.screens.personal.PersonalProfileEditor
import com.example.intelligent_shopping_cart.ui.screens.register.Register
import com.example.intelligent_shopping_cart.ui.theme.Intelligent_shopping_cartTheme
import com.example.intelligent_shopping_cart.utils.LocalNavController
import com.example.intelligent_shopping_cart.view_model.CommodityIntent
import com.example.intelligent_shopping_cart.view_model.CommodityViewModel
import com.example.intelligent_shopping_cart.view_model.UserViewModel
import com.example.intelligent_shopping_cart.view_model.shoppingCartCommodityListMock
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false) // 设置沉浸式状态栏
        setContent {
            Intelligent_shopping_cartTheme {
                val useDarkIcons = isSystemInDarkTheme()
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setSystemBarsColor(Color.Transparent, true)
                }
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                CompositionLocalProvider(
                    LocalNavController provides navController,
                ) {
                    ShoppingCartNavHost(navController, this)
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ShoppingCartNavHost(
    navController: NavHostController,
    mainActivity: MainActivity,
) {
    NavHost(
        navController = navController,
        startDestination = AppScreen.login,
    ) {
        composable(
            AppScreen.login,
        ) {
            val userViewModel = hiltViewModel<UserViewModel>(mainActivity)
            Login(userViewModel)
        }
        composable(
            AppScreen.register,
        ) {
            val userViewModel = hiltViewModel<UserViewModel>(mainActivity)

            Register(userViewModel)
        }
//        home
        composable(
            AppScreen.home,
        ) { backStackEntry ->
            val userViewModel = hiltViewModel<UserViewModel>(mainActivity)
//            val userViewModel = hiltViewModel<UserViewModel>(backStackEntry)

            val commodityViewModel = hiltViewModel<CommodityViewModel>(mainActivity)
//            val commodityViewModel = hiltViewModel<UserViewModel>(backStackEntry)
            AppScaffold(userViewModel, commodityViewModel)
        }
//        个人信息编辑页
        composable(
            route = "${AppScreen.profileEdit}/{category}",
            arguments = listOf(navArgument("category") {
                type = NavType.StringType
            }),
        ) { backStackEntry ->
            Log.d("test", "profileEdit: $backStackEntry")
            val category = backStackEntry.arguments?.getString("category")
            val viewModel = hiltViewModel<UserViewModel>(mainActivity)
//            val viewModel = hiltViewModel<UserViewModel>(backStackEntry)
            PersonalProfileEditor(category, viewModel)
        }
//        点击某一类别的商品后，展示的商品列表
        composable(
            route = "${AppScreen.commodityList}/{commodityTypeId}",
            arguments = listOf(navArgument("commodityTypeId") {
                type = NavType.StringType
            }),
        ) { backStackEntry ->
            var commodityTypeId = backStackEntry.arguments?.getString("commodityTypeId")
            val viewModel = hiltViewModel<CommodityViewModel>(mainActivity)
            viewModel.dispatch(CommodityIntent.LoadCommodities(commodityTypeId!!))
            CommodityList(commodityTypeId, viewModel)
        }
//        商品详情页
        composable(
            route = "${AppScreen.commodityDetail}/{commodityId}?commodityTypeId={commodityTypeId}",
            arguments = listOf(navArgument("commodityTypeId") {
                type = NavType.StringType
                defaultValue = "-1"
            }, navArgument("commodityId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val commodityViewModel = hiltViewModel<CommodityViewModel>(mainActivity)

            var commodityTypeId = backStackEntry.arguments?.getString("commodityTypeId")
            var commodityId = backStackEntry.arguments?.getString("commodityId")
//如果是-1，则是从购物车点击进入。
            val commodity = if (commodityTypeId == "-1") {
                commodityViewModel.getCommodityById(commodityId!!)
            } else {
                commodityViewModel.getCommodityByTypeIdAndId(commodityTypeId!!, commodityId!!)
            } ?: shoppingCartCommodityListMock[0]

            CommodityDetail(commodity)

        }
    }


}

