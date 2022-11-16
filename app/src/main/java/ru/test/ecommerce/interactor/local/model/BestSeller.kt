package ru.test.ecommerce.interactor.local.model

import ru.test.ecommerce.ui.main.adapter.MainListItem

data class BestSeller(
    val id: Long,
    val isFavorites: Boolean,
    val title: String,
    val priceWithoutDiscount: String,
    val discountPrice: String,
    val picture: String
) : MainListItem {
    override val itemId: Long = id.hashCode().toLong()
}
