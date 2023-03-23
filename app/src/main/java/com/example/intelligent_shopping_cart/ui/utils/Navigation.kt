package com.example.intelligent_shopping_cart.ui.utils

import android.util.Log
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder

val LocalNavController = compositionLocalOf<NavHostController> {
    error("CompositionLocal LocalNavController not present")
}

fun NavOptionsBuilder.popUpAllBackStackEntry(navController: NavHostController) {

    navController.backQueue.forEach {
        Log.d("zlf", "popUpAllBackStackEntry:${it.destination.route} ")
    }

    navController.backQueue.reversed().forEach {
        it.destination.route?.let { route ->
            this.popUpTo(route) { inclusive = true }
        }
    }
}
