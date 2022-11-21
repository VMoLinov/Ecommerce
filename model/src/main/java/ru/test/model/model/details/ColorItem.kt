package ru.test.model.model.details

data class ColorItem(
    val color: String,
    override var isActive: Boolean = false
) : DetailsListItem {
    override val itemId: Long = color.hashCode().toLong()
}
