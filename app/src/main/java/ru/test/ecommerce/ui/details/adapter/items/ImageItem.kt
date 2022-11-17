package ru.test.ecommerce.ui.details.adapter.items

import ru.test.ecommerce.ui.details.adapter.DetailsListItem

data class ImageItem(
    val url: String,
    override var isActive: Boolean = false
) : DetailsListItem {
    override val itemId: Long = url.hashCode().toLong()
}
