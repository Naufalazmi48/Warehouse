package com.example.latihanjetpackcompose.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.latihanjetpackcompose.DataDummy
import com.example.latihanjetpackcompose.Product
import com.example.latihanjetpackcompose.Routes

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun ProductScreen(navController: NavController) {
    val searchText = remember { mutableStateOf("") }
    val searchViewVisibility = remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Text(text = "Product")
                        Spacer(modifier = Modifier.width(32.dp))
                        AnimatedVisibility(
                            visible = searchViewVisibility.value,
                            enter = fadeIn(initialAlpha = 0.4f),
                            exit = fadeOut(animationSpec = tween(durationMillis = 250))
                        ) {
                            TextField(value = searchText.value, onValueChange = {
                                searchText.value = it
                            }, placeholder = {
                                Text(text = "Cari Produk...", color = Color.White)
                            }, trailingIcon = {
                                IconButton(onClick = { searchViewVisibility.value = false }) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Batalkan pencarian",
                                        tint = Color.White
                                    )
                                }
                            },
                                modifier = Modifier.apply {
                                    fillMaxWidth()
                                    fillMaxHeight()
                                })
                        }
                    }
                },
                actions = {
                    if (!searchViewVisibility.value) {
                        IconButton(
                            onClick = { searchViewVisibility.value = true },
                            Modifier.padding(end = 12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search Product"
                            )
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Routes.AddProduct.route) }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Product",
                    tint = Color.White
                )
            }
        }
    ) {
        ProductList(navController, searchText.value)
    }
}

@ExperimentalMaterialApi
@Composable
fun ProductList(navController: NavController, searchText: String) {
    val listDummy =
        if (searchText.isEmpty()) DataDummy.listProduct else DataDummy.listProduct.filter { productItem ->
            productItem.name.contains(searchText, true)
        }
    LazyColumn {
        items(listDummy.size) { index ->
            ProductItem(product = listDummy[index]) {
                navController.navigate(Routes.ProductDetail.route + "/${it.id}")
            }

        }
    }
}

@ExperimentalMaterialApi
@Composable
fun ProductItem(product: Product, onClick: (Product) -> Unit) {
    Card(
        onClick = { onClick.invoke(product) },
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
    ) {
        Column(Modifier.padding(32.dp)) {
            Text(text = product.name)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Rp.${product.price}")
        }
    }
}

