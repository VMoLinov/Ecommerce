package ru.test.model

interface MainListItem {
    val itemId: Long

    override fun equals(other: Any?): Boolean
}
