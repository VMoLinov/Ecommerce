package ru.test.domain.detailsscreen.model

data class ColorItem(
    val color: String,
    override var isActive: Boolean = false
) : DetailsListItem {
    override val itemId: Long = color.hashCode().toLong()
}
