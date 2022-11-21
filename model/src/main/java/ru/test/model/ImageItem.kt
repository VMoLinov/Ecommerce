package ru.test.model

import ru.test.model.model.DetailsListItem

data class ImageItem(
    val url: String,
    override var isActive: Boolean = false
) : DetailsListItem {
    override val itemId: Long = url.hashCode().toLong()
}
