package ru.test.ecommerce.ui.details.adapter.items

import ru.test.ecommerce.ui.details.adapter.DetailsListItem

data class ColorItem(
    val color: String,
    override var isActive: Boolean = false
) : DetailsListItem {
    override val itemId: Long = color.hashCode().toLong()
}
