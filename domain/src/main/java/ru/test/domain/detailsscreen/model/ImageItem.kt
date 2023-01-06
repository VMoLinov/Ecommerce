package ru.test.domain.detailsscreen.model

data class ImageItem(
    val url: String,
    override var isActive: Boolean = false
) : DetailsListItem {
    override val itemId: Long = url.hashCode().toLong()
}
