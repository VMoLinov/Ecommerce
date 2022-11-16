package ru.test.ecommerce.interactor.local

import android.content.Context
import ru.test.ecommerce.interactor.local.model.BestSeller
import ru.test.ecommerce.interactor.local.model.HomeStore
import ru.test.ecommerce.ui.main.adapter.MainListItem
import ru.test.ecommerce.ui.main.adapter.items.BestSellerVerticalItemMain
import ru.test.ecommerce.ui.main.adapter.items.CategoriesHorizontalItemMain
import ru.test.ecommerce.ui.main.adapter.items.HotSalesHorizontalItemMain
import javax.inject.Inject

class AppResourceProvider @Inject constructor(
    private val context: Context
) : ResourceProvider {

    override fun string(id: Int): String = context.resources.getString(id)

    override fun wrapDataWithConstants(
        homeStores: List<HomeStore>,
        bestSellers: List<BestSeller>
    ): List<MainListItem> {
        return listOf(
            CategoriesHorizontalItemMain(),
            HotSalesHorizontalItemMain(homeStores),
            BestSellerVerticalItemMain(bestSellers)
        )
    }
}
