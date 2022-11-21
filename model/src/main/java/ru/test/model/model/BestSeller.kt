package ru.test.model.model

import ru.test.model.MainListItem


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
