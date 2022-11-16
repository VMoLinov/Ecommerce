package ru.test.ecommerce.detailsadapter

interface DetailsListItem {
    val itemId: Long

    override fun equals(other: Any?): Boolean
}
