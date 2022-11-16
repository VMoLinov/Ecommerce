package ru.test.ecommerce.repository.model

import ru.test.ecommerce.mainadapter.MainListItem

data class HomeStoreDTO(
    val id: Long,
    val is_new: Boolean,
    val title: String,
    val subtitle: String,
    val picture: String,
    val is_buy: Boolean
) : MainListItem {
    override val itemId: Long = id.hashCode().toLong()
}
