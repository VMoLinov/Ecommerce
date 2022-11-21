package ru.test.model.model.cart

data class Basket(
    val id: Long,
    val images: String,
    val priceNum: Int,
    val title: String,
    var quantity: Int
)
