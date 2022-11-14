package ru.test.ecommerce.bestseller

import ru.test.ecommerce.adapter.ListItem

data class BestSellerVerticalItem(
    val title: String,
    val categories: List<ListItem>
) : ListItem {
    override val itemId = title.hashCode().toLong()
}
