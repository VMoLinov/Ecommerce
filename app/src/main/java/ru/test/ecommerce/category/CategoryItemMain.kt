package ru.test.ecommerce.category

import ru.test.ecommerce.mainadapter.MainListItem

data class CategoryItemMain(
    val name: Int,
    val icon: Int,
    var isActive: Boolean = false
) : MainListItem {
    override val itemId = name.hashCode().toLong()
}
