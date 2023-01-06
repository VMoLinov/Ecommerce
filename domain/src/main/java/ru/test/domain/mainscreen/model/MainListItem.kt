package ru.test.domain.mainscreen.model

interface MainListItem {
    val itemId: Long

    override fun equals(other: Any?): Boolean
}
