package ru.test.model.model

data class Cart(
    var basket: List<Basket>,
    val delivery: String,
    val id: String,
    var total: String
)
