package ru.test.ecommerce.detailsadapter

data class ColorItem(
    val color: String
) : DetailsListItem {
    override val itemId: Long = color.hashCode().toLong()
}
