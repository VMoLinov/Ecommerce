package ru.test.model.model.details

data class ImageItem(
    val url: String,
    override var isActive: Boolean = false
) : DetailsListItem {
    override val itemId: Long = url.hashCode().toLong()
}
