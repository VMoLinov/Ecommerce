package ru.test.domain.mainscreen.model

data class Category(
    val name: Int,
    val icon: Int,
    var isActive: Boolean = false
) : MainListItem {
    override val itemId = name.hashCode().toLong()
}
