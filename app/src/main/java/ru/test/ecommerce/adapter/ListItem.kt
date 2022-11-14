package ru.test.ecommerce.adapter

interface ListItem {
    val itemId: Long

    override fun equals(other: Any?): Boolean
}
