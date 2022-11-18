package ru.test.ecommerce.interactor.network.model

data class BasketDTO(
    val id: Long,
    val images: String,
    val price: Int,
    val title: String
)
