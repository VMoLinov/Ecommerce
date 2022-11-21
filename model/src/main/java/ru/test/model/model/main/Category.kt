package ru.test.model.model.main

import ru.test.model.model.main.MainListItem

data class Category(
    val name: Int,
    val icon: Int,
    var isActive: Boolean = false
) : MainListItem {
    override val itemId = name.hashCode().toLong()
}
