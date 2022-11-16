package ru.test.ecommerce.hotsales

import ru.test.ecommerce.R
import ru.test.ecommerce.mainadapter.MainListItem
import ru.test.ecommerce.repository.model.HomeStoreDTO

data class HotSalesHorizontalItemMain(
    val title: Int,
    val endTitle: Int,
    val items: List<HomeStoreDTO>
) : MainListItem {
    override val itemId: Long = title.hashCode().toLong()

    constructor(items: List<HomeStoreDTO>) : this(
        title = R.string.hot_sales,
        endTitle = R.string.see_more,
        items = items
    )
}
