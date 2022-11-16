package ru.test.ecommerce.detailsadapter

data class MemoryItem(
    val size: String
) : DetailsListItem {
    override val itemId: Long = size.hashCode().toLong()
}
