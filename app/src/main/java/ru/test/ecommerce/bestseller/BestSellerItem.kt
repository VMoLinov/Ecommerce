package ru.test.ecommerce.bestseller

import ru.test.ecommerce.adapter.ListItem

data class BestSellerItem(
    val id: Long,
    val title: String,
    val oldPrice: String,
    val price: String,
    val isFavorites: Boolean = false,
    val image: String
) : ListItem {
    override val itemId: Long = id.hashCode().toLong()
}
