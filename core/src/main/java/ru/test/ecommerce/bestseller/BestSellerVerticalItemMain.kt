package ru.test.ecommerce.bestseller

import ru.test.ecommerce.R
import ru.test.ecommerce.mainadapter.MainListItem
import ru.test.ecommerce.repository.model.BestSellerDTO

data class BestSellerVerticalItemMain(
    val title: Int,
    val endTitle: Int,
    val items: List<BestSellerDTO>
) : MainListItem {
    override val itemId = title.hashCode().toLong()

    constructor(items: List<BestSellerDTO>) : this(
        title = R.string.best_seller,
        endTitle = R.string.see_more,
        items = items
    )
}
