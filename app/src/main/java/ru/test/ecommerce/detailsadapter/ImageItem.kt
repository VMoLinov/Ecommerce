package ru.test.ecommerce.detailsadapter

data class ImageItem(
    val url: String
) : DetailsListItem {
    override val itemId: Long = url.hashCode().toLong()
}
