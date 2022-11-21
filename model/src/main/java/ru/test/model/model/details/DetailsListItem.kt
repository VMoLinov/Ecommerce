package ru.test.model.model.details

interface DetailsListItem {
    val itemId: Long
    var isActive: Boolean

    override fun equals(other: Any?): Boolean
}
