package ru.test.ecommerce.hotsales

import ru.test.ecommerce.adapter.ListItem

data class HotSaleItem(
    val name: String
) : ListItem {
    override val itemId = name.hashCode().toLong()
}
