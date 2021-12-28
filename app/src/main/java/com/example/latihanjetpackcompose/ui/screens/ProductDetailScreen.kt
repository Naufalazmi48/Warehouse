package com.example.latihanjetpackcompose.ui.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import com.example.latihanjetpackcompose.DataDummy
import com.example.latihanjetpackcompose.Product
import com.example.latihanjetpackcompose.Router

@ExperimentalMaterialApi
@Composable
fun ProductDetailScreen(id: Int) {
    val router = Router.current
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Product Detail") },
            navigationIcon = {
                IconButton(onClick = { router.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Kembali ke halaman utama"
                    )
                }
            }
        )
    }) {
        ProductDetailBody(product = DataDummy.listProduct.filter { it.id.toString().contains(id.toString()) }[0])
    }
}

@ExperimentalMaterialApi
@Composable
fun ProductDetailBody(product: Product) {
    ProductItem(product = product) {
        
    }
}