package ru.test.ecommerce.ui.main.adapter

interface MainListItem {
    val itemId: Long

    override fun equals(other: Any?): Boolean
}
