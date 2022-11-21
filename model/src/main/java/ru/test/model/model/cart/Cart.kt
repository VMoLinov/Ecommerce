package ru.test.model.model.cart

data class Cart(
    var basket: List<Basket>,
    val delivery: String,
    val id: String,
    var total: String
)
