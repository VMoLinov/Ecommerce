package ru.test.domain.cartscreen.model

data class Basket(
    val id: Long,
    val images: String,
    val priceNum: Int,
    val title: String,
    var quantity: Int
)
