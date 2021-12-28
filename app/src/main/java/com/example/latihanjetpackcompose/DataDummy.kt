package com.example.latihanjetpackcompose

data class Product(
    val id: Int,
    val name: String,
    val price: Int
)

object DataDummy {
    val listProduct = arrayListOf(
        Product(id = 0, name = "Kitkat", 10000),
        Product(id = 1, name = "Oreo", 15000),
        Product(id = 2, name = "Tango", 9000),
        Product(id = 3, name = "Chitato", 20000),
        Product(id = 4, name = "Nabati", 8000),
    )
}