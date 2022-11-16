package ru.test.ecommerce.repository.model

import ru.test.ecommerce.mainadapter.MainListItem

data class BestSellerDTO(
    val id: Long,
    val is_favorites: Boolean,
    val title: String,
    val price_without_discount: Int,
    val discount_price: Int,
    val picture: String
) : MainListItem {
    override val itemId: Long = id.hashCode().toLong()
}
