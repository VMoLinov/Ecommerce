package ru.test.ecommerce.hotsales

import ru.test.ecommerce.adapter.ListItem

data class HotSalesHorizontalItem(
    val title: String,
    val endTitle: String,
    val categories: List<HotSaleItem>
) : ListItem {
    override val itemId: Long = title.hashCode().toLong()
}
