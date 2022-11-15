package ru.test.ecommerce.bestseller

import ru.test.ecommerce.adapter.ListItem

data class BestSellerVerticalItem(
    val title: String,
    val endTitle: String,
    val categories: List<BestSellerItem>
) : ListItem {
    override val itemId = title.hashCode().toLong()
}
