package com.example.latihanjetpackcompose

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController

val Router = compositionLocalOf<NavHostController> { error("No active user found!") }

sealed class Routes(val route: String) {
    object Product : Routes("product")
    object ProductDetail : Routes("product/detail")
    object AddProduct : Routes("product/add")
}