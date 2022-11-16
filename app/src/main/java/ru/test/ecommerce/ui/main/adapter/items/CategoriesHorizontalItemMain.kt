package ru.test.ecommerce.ui.main.adapter.items

import ru.test.ecommerce.R
import ru.test.ecommerce.interactor.local.model.Category
import ru.test.ecommerce.ui.main.adapter.MainListItem

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
