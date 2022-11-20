package ru.test.network.model

data class BestSellerDTO(
    val id: Long,
    val is_favorites: Boolean,
    val title: String,
    val price_without_discount: Int,
    val discount_price: Int,
    val picture: String
)
