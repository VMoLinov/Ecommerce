package ru.test.ecommerce.ui.details.adapter

interface DetailsListItem {
    val itemId: Long
    var isActive: Boolean

    override fun equals(other: Any?): Boolean
}
