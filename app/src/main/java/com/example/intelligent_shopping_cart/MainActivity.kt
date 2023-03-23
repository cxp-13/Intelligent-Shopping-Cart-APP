package com.example.intelligent_shopping_cart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.chatty.compose.screens.drawer.PersonalProfileEditor
import com.chatty.compose.screens.login.Login
import com.chatty.compose.screens.register.Register
import com.example.intelligent_shopping_cart.ui.components.AppScaffold
import com.example.intelligent_shopping_cart.ui.components.AppScreen
import com.example.intelligent_shopping_cart.ui.theme.Intelligent_shopping_cartTheme
import com.example.intelligent_shopping_cart.ui.utils.LocalNavController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Intelligent_shopping_cartTheme {
                val navController = rememberAnimatedNavController()


                CompositionLocalProvider(
                    LocalNavController provides navController,
                ) {
                    ShoppingCartNavHost(navController)
                }
            }


        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ShoppingCartNavHost(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = AppScreen.login,
    ) {
        composable(
            AppScreen.login,
            enterTransition = null,
            exitTransition = null
        ) {
            Login()
        }
        composable(
            AppScreen.register,
            enterTransition = null,
            exitTransition = null
        ) {
            Register()
        }
        composable(
            AppScreen.main,
            enterTransition = null,
            exitTransition = null,
        ) {
            AppScaffold()
        }
        composable(
            route = "${AppScreen.profileEdit}/{category}",
            arguments = listOf(navArgument("category") {
                type = NavType.StringType
            }),
            enterTransition = null,
            exitTransition = null
        ) { backStackEntry ->
            var category = backStackEntry.arguments?.getString("category")!!
            PersonalProfileEditor(category)
        }

    }
}

