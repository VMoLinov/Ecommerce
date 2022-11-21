package ru.test.model

import ru.test.model.model.HomeStore

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
