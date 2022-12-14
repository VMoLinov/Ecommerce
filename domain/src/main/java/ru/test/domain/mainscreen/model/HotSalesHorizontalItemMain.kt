package ru.test.domain.mainscreen.model

import ru.test.domain.R

data class HotSalesHorizontalItemMain(
    val title: Int,
    val endTitle: Int,
    val items: List<HomeStore>
) : MainListItem {
    override val itemId: Long = title.hashCode().toLong()

    constructor(items: List<HomeStore>) : this(
        title = R.string.hot_sales,
        endTitle = R.string.see_more,
        items = items
    )
}
