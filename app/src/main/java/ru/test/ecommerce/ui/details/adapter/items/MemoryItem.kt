package ru.test.ecommerce.ui.details.adapter.items

import ru.test.ecommerce.ui.details.adapter.DetailsListItem

data class MemoryItem(
    val size: String
) : DetailsListItem {
    override val itemId: Long = size.hashCode().toLong()
}
