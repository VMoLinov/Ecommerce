package ru.test.repository.network.model

data class CartDTO(
    val basket: List<BasketDTO>,
    val delivery: String,
    val id: String,
    val total: String
)
