package ru.test.ecommerce.ui.main.adapter.items

import ru.test.ecommerce.R
import ru.test.ecommerce.interactor.local.model.HomeStore
import ru.test.ecommerce.ui.main.adapter.MainListItem

data class HotSalesHorizontalItemMain(
    val title: Int,
    val endTitle: Int,
    val items: List<HomeStore>
) : MainListItem {
    override val itemId: Long = title.hashCode().toLong()

    constructor(items: List<HomeStore>) : this(
        title = R.string.hot_sales,
        endTitle = R.string.see_more,
        items = items
    )
}
