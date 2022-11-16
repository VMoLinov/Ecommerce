package ru.test.ecommerce.ui.details.adapter

interface DetailsListItem {
    val itemId: Long

    override fun equals(other: Any?): Boolean
}
