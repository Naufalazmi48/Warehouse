package com.example.latihanjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.latihanjetpackcompose.ui.screens.AddProductScreen
import com.example.latihanjetpackcompose.ui.screens.ProductDetailScreen
import com.example.latihanjetpackcompose.ui.screens.ProductScreen
import com.example.latihanjetpackcompose.ui.theme.WarehouseTheme

@ExperimentalMaterialApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WarehouseTheme {
                Surface {
                    val navController = rememberNavController()
                    CompositionLocalProvider(Router provides navController) {
                        MainScreen()
                    }
                }
            }
        }
    }

}
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun MainScreen() {
    val navController = Router.current
    NavHost(navController = navController, startDestination = Routes.Product.route) {
        composable(Routes.Product.route) {
            ProductScreen(navController = navController)
        }
        composable(Routes.ProductDetail.route+ "/{id}") {
            it.arguments?.getString("id")?.let { id ->
                ProductDetailScreen(id = id.toInt()) }
        }
        composable(Routes.AddProduct.route) {
            AddProductScreen()
        }
    }
}