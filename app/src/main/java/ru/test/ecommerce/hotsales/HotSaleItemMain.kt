package ru.test.ecommerce.hotsales

import ru.test.ecommerce.mainadapter.MainListItem

data class HotSaleItemMain(
    val name: String
) : MainListItem {
    override val itemId = name.hashCode().toLong()
}
