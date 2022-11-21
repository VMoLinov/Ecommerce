package ru.test.model

import ru.test.model.model.DetailsListItem

data class ColorItem(
    val color: String,
    override var isActive: Boolean = false
) : DetailsListItem {
    override val itemId: Long = color.hashCode().toLong()
}
