package ru.test.ecommerce.category

import ru.test.ecommerce.R
import ru.test.ecommerce.mainadapter.MainListItem

data class CategoriesHorizontalItemMain(
    val title: Int,
    val endTitle: Int,
    val categories: List<CategoryItemMain>
) : MainListItem {
    override val itemId: Long = title.hashCode().toLong()

    constructor() : this(
        title = R.string.select_category,
        endTitle = R.string.view_all,
        categories = listOf(
            CategoryItemMain(R.string.ic_phones, R.drawable.ic_phones),
            CategoryItemMain(R.string.ic_computer, R.drawable.ic_computer),
            CategoryItemMain(R.string.ic_health, R.drawable.ic_health),
            CategoryItemMain(R.string.ic_books, R.drawable.ic_books),
            CategoryItemMain(R.string.ic_tools, R.drawable.ic_tools)
        )
    )
}
