package ru.test.ecommerce.interactor.network.model

data class CartDTO(
    val basket: List<BasketDTO>,
    val delivery: String,
    val id: String,
    val total: String
)
