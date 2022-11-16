package ru.test.ecommerce.ui.main.adapter.items

import ru.test.ecommerce.R
import ru.test.ecommerce.interactor.local.model.BestSeller
import ru.test.ecommerce.ui.main.adapter.MainListItem

data class BestSellerVerticalItemMain(
    val title: Int,
    val endTitle: Int,
    val items: List<BestSeller>
) : MainListItem {
    override val itemId = title.hashCode().toLong()

    constructor(items: List<BestSeller>) : this(
        title = R.string.best_seller,
        endTitle = R.string.see_more,
        items = items
    )
}
