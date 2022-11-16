package ru.test.ecommerce.interactor.local.model

import ru.test.ecommerce.ui.main.adapter.MainListItem

data class Category(
    val name: Int,
    val icon: Int,
    var isActive: Boolean = false
) : MainListItem {
    override val itemId = name.hashCode().toLong()
}
