package ru.test.ecommerce.interactor.network.model

data class HomeStoreDTO(
    val id: Long,
    val is_new: Boolean,
    val title: String,
    val subtitle: String,
    val picture: String,
    val is_buy: Boolean
)
