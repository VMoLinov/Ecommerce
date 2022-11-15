package ru.test.ecommerce.mainadapter

interface MainListItem {
    val itemId: Long

    override fun equals(other: Any?): Boolean
}
