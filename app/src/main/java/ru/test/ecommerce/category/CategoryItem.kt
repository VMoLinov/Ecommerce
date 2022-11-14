package ru.test.ecommerce.category

import ru.test.ecommerce.adapter.ListItem

data class CategoryItem(
    val name: Int,
    val icon: Int,
    var isActive: Boolean = false
) : ListItem {
    override val itemId = name.hashCode().toLong()
}
