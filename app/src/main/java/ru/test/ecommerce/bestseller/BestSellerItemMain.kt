package ru.test.ecommerce.bestseller

import ru.test.ecommerce.mainadapter.MainListItem

data class BestSellerItemMain(
    val id: Long,
    val title: String,
    val oldPrice: String,
    val price: String,
    val isFavorites: Boolean = false,
    val image: String
) : MainListItem {
    override val itemId: Long = id.hashCode().toLong()
}
