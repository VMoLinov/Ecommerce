package ru.test.ecommerce.hotsales

import ru.test.ecommerce.mainadapter.MainListItem

data class HotSalesHorizontalItemMain(
    val title: String,
    val endTitle: String,
    val categories: List<HotSaleItemMain>
) : MainListItem {
    override val itemId: Long = title.hashCode().toLong()
}
