package ru.test.ecommerce.interactor.local.model

import ru.test.ecommerce.ui.main.adapter.MainListItem

data class HomeStore(
    val id: Long,
    val isNew: Boolean,
    val title: String,
    val subtitle: String,
    val picture: String,
    val isBuy: Boolean
) : MainListItem {
    override val itemId = id.hashCode().toLong()
}
