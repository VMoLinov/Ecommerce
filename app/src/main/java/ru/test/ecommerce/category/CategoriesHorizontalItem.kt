package ru.test.ecommerce.category

import ru.test.ecommerce.R
import ru.test.ecommerce.adapter.ListItem

data class CategoriesHorizontalItem(
    val title: Int,
    val endTitle: Int,
    val categories: List<CategoryItem>
) : ListItem {
    override val itemId: Long = title.hashCode().toLong()

    constructor() : this(
        title = R.string.select_category,
        endTitle = R.string.view_all,
        categories = listOf(
            CategoryItem(R.string.ic_phones, R.drawable.ic_phones),
            CategoryItem(R.string.ic_computer, R.drawable.ic_computer),
            CategoryItem(R.string.ic_health, R.drawable.ic_health),
            CategoryItem(R.string.ic_books, R.drawable.ic_books),
            CategoryItem(R.string.ic_tools, R.drawable.ic_tools)
        )
    )
}
