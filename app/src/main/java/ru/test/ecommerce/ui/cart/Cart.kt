package ru.test.ecommerce.ui.cart

data class Cart(
    var basket: MutableList<Basket>,
    val delivery: String,
    val id: String,
    var total: String
)
