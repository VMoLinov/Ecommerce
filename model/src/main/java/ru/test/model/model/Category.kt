package ru.test.model.model

import ru.test.model.MainListItem

data class Category(
    val name: Int,
    val icon: Int,
    var isActive: Boolean = false
) : MainListItem {
    override val itemId = name.hashCode().toLong()
}
