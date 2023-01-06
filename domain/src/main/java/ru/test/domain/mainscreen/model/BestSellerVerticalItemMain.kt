package ru.test.domain.mainscreen.model

import ru.test.domain.R


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
