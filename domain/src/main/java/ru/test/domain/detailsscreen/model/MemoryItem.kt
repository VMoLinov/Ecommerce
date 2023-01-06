package ru.test.domain.detailsscreen.model

data class MemoryItem(
    val size: String,
    override var isActive: Boolean = false
) : DetailsListItem {
    override val itemId: Long = size.hashCode().toLong()
}
