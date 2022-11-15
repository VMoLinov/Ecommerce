package ru.test.ecommerce.bestseller

import ru.test.ecommerce.mainadapter.MainListItem

data class BestSellerVerticalItemMain(
    val title: String,
    val endTitle: String,
    val categories: List<BestSellerItemMain>
) : MainListItem {
    override val itemId = title.hashCode().toLong()
}
