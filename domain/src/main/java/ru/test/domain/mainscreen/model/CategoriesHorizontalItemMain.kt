package ru.test.domain.mainscreen.model

import ru.test.domain.R

data class CategoriesHorizontalItemMain(
    val title: Int,
    val endTitle: Int,
    val categories: List<Category>
) : MainListItem {
    override val itemId: Long = title.hashCode().toLong()

    constructor() : this(
        title = R.string.select_category,
        endTitle = R.string.view_all,
        categories = listOf(
            Category(R.string.ic_phones, R.drawable.ic_phones),
            Category(R.string.ic_computer, R.drawable.ic_computer),
            Category(R.string.ic_health, R.drawable.ic_health),
            Category(R.string.ic_books, R.drawable.ic_books),
            Category(R.string.ic_tools, R.drawable.ic_tools)
        )
    )
}
