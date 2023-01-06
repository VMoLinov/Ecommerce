package ru.test.domain.mainscreen.model

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
