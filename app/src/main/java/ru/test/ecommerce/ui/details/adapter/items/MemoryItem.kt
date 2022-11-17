package ru.test.ecommerce.ui.details.adapter.items

import ru.test.ecommerce.ui.details.adapter.DetailsListItem

data class MemoryItem(
    val size: String,
    override var isActive: Boolean = false
) : DetailsListItem {
    override val itemId: Long = size.hashCode().toLong()
}
