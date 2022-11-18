package ru.test.ecommerce.ui.cart

data class Basket(
    val id: Long,
    val images: String,
    val priceNum: Int,
    val title: String,
    var quantity: Int
)
