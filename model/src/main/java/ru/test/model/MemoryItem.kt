package ru.test.model

import ru.test.model.model.DetailsListItem

data class MemoryItem(
    val size: String,
    override var isActive: Boolean = false
) : DetailsListItem {
    override val itemId: Long = size.hashCode().toLong()
}
