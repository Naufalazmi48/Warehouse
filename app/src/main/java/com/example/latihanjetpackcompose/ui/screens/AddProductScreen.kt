package com.example.latihanjetpackcompose.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.latihanjetpackcompose.DataDummy
import com.example.latihanjetpackcompose.Product
import com.example.latihanjetpackcompose.Router

@Composable
fun AddProductScreen() {
    val router = Router.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Add Product") },
                navigationIcon = {
                    IconButton(onClick = { router.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Kembali ke halaman utama"
                        )
                    }
                }
            )
        }
    ) {
        AddProductForm(router)
    }
}

@Composable
fun AddProductForm(router: NavHostController) {
    val name = remember { mutableStateOf("") }
    val price = remember { mutableStateOf("") }
    val context = LocalContext.current


    Column(Modifier.padding(12.dp)) {
        TextField(
            value = name.value,
            onValueChange = { name.value = it },
            placeholder = { Text(text = "Nama Produk...") })
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            value = price.value,
            onValueChange = { price.value = it.filter { char -> char.isDigit() } },
            placeholder = { Text(text = "Rp.0") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {
            val newProduct = Product(DataDummy.listProduct.size, name.value, price = price.value.toInt())
            DataDummy.listProduct.add(newProduct)

            Toast.makeText(context, "Produk berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            router.navigateUp()
        }) {
            Text(text = "Tambahkan", color = Color.White)
        }
    }
}