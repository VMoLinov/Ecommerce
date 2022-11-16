package ru.test.ecommerce.interactor.network.model

import ru.test.ecommerce.ui.main.adapter.MainListItem

data class BestSellerDTO(
    val id: Long,
    val is_favorites: Boolean,
    val title: String,
    val price_without_discount: Int,
    val discount_price: Int,
    val picture: String
)
