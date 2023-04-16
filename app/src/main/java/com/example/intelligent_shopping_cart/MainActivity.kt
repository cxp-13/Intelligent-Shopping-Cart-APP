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
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.intelligent_shopping_cart.ui.components.AppScaffold
import com.example.intelligent_shopping_cart.ui.components.AppScreen
import com.example.intelligent_shopping_cart.ui.screens.commodity_details.CommodityDetailScreen
import com.example.intelligent_shopping_cart.ui.screens.commodity_list.CommodityListScreen
import com.example.intelligent_shopping_cart.ui.screens.login.LoginScreen
import com.example.intelligent_shopping_cart.ui.screens.map.GaoDeMap
import com.example.intelligent_shopping_cart.ui.screens.map.TencentMap
import com.example.intelligent_shopping_cart.ui.screens.personal.PersonalProfileEditorScreen
import com.example.intelligent_shopping_cart.ui.screens.register.RegisterScreen
import com.example.intelligent_shopping_cart.ui.theme.Intelligent_shopping_cartTheme
import com.example.intelligent_shopping_cart.utils.GDMapUtils
import com.example.intelligent_shopping_cart.utils.LocalNavController
import com.example.intelligent_shopping_cart.utils.TencentMapUtils
import com.example.intelligent_shopping_cart.view_model.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

// 指示该 Activity 应该作为入口点被 Hilt 用于依赖注入
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置 Activity 使用全屏沉浸模式，没有系统 UI 元素可见
        WindowCompat.setDecorFitsSystemWindows(window, false)

        GDMapUtils.updateMapViewPrivacy(applicationContext)

        TencentMapUtils.setAgreePrivacy()

        setContent {
            // 使用 Jetpack Compose 设置 Activity 的内容
            Intelligent_shopping_cartTheme {
                // 确定当前是否处于暗色主题
                val useDarkIcons = isSystemInDarkTheme()
                // 创建一个 SystemUiController 实例，可用于控制系统 UI 元素
                val systemUiController = rememberSystemUiController()
                // 应用一个副作用将系统栏颜色设置为透明
                SideEffect {
                    systemUiController.setSystemBarsColor(Color.Transparent, true)
                }
                // 创建一个 NavController 实例，可用于在应用程序中的不同屏幕之间导航
                val navController = rememberNavController()
                // 动画
//                val navAnimatedController = rememberAnimatedNavController()
                // 将 NavController 实例提供给子组件
                CompositionLocalProvider(
                    LocalNavController provides navController,
                ) {
                    // 定义应用程序中的导航路由
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
            route = "${AppScreen.tencentMap}/{commodityId}",
            arguments = listOf(navArgument("commodityId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val tencentMapViewModel: TencentMapViewModel = hiltViewModel()
            val commodityId = backStackEntry.arguments?.getInt("commodityId")
            tencentMapViewModel.dispatch(
                TencentMapIntent.LoadingWalkPlan(
                    commodityId = commodityId ?: 1
                )
            )
            TencentMap(tencentMapViewModel)
        }
//        composable(
//            route = "test"
//        ) {
//            val tencentMapViewModel: TencentMapViewModel = hiltViewModel()
//            tencentMapViewModel.dispatch(
//                TencentMapIntent.LoadingWalkPlan(
//                    commodityId = 3
//                )
//            )
//            TencentMap(tencentMapViewModel)
//        }
        composable(
            AppScreen.gaodeMap,
        ) {
            val gaoDeViewModel: GaoDeMapViewModel = hiltViewModel()
            GaoDeMap(gaoDeViewModel)
        }
        composable(
            AppScreen.login,
        ) {
            val userViewModel = hiltViewModel<UserViewModel>(mainActivity)
            LoginScreen(userViewModel)
        }
        composable(
            AppScreen.register,
        ) {
            val userViewModel = hiltViewModel<UserViewModel>(mainActivity)
            RegisterScreen(userViewModel)
        }
//        home
        composable(
            AppScreen.home,
        ) { backStackEntry ->
            val userViewModel = hiltViewModel<UserViewModel>(mainActivity)
//            val userViewModel = hiltViewModel<UserViewModel>(backStackEntry)
            val commodityViewModel = hiltViewModel<CommodityViewModel>(mainActivity)
//            val commodityViewModel = hiltViewModel<UserViewModel>(backStackEntry)
            Log.d("viewModel_log", "ShoppingCartNavHost-home: $commodityViewModel")
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
            PersonalProfileEditorScreen(category, viewModel)
        }
//        点击某一类别的商品后，展示的商品列表
        composable(
            route = "${AppScreen.commodityList}/{commodityType}",
            arguments = listOf(navArgument("commodityType") {
                type = NavType.StringType
            }),
        ) { backStackEntry ->
            val commodityType = backStackEntry.arguments?.getString("commodityType")
            val viewModel = hiltViewModel<CommodityViewModel>(mainActivity)
            viewModel.dispatch(CommodityIntent.SelectType(commodityType!!))
            Log.d("viewModel_log", "ShoppingCartNavHost-commodityList: $viewModel")
            CommodityListScreen(viewModel)
        }
//        商品详情页
        composable(
            route = "${AppScreen.commodityDetail}/{commodityId}",
            arguments = listOf(navArgument("commodityId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val commodityViewModel = hiltViewModel<CommodityViewModel>(mainActivity)

            val commodityId = backStackEntry.arguments?.getInt("commodityId")
            Log.d("viewModel_log", "ShoppingCartNavHost-commodityDetail: $commodityViewModel")

            commodityViewModel.dispatch(CommodityIntent.SelectCommodity(commodityId!!))
            CommodityDetailScreen(commodityViewModel)

        }
    }
}

