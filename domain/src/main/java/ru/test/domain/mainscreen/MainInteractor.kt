package ru.test.domain.mainscreen

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import ru.test.domain.interactor.toBestSeller
import ru.test.domain.interactor.toHomeStore
import ru.test.domain.mainscreen.model.*
import ru.test.repository.local.ResourceProvider
import ru.test.repository.network.NetworkSource
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val local: ResourceProvider,
    private val network: NetworkSource
) {

    suspend fun getData(): kotlinx.coroutines.flow.Flow<List<MainListItem>> {
        val data = network.getMainList()
        return flow {
            emit(
                wrapDataWithConstants(
                    data.first().home_store.map { it.toHomeStore() },
                    data.first().best_seller.map { it.toBestSeller() }
                )
            )
        }
    }

    fun getBrands(): List<String> = local.getBrands()

    fun getPrices(): List<String> = local.getPrices()

    fun getSizes(): List<String> = local.getSizes()

    private fun wrapDataWithConstants(
        homeStores: List<HomeStore>, bestSellers: List<BestSeller>
    ): List<MainListItem> {
        return listOf(
            CategoriesHorizontalItemMain(),
            HotSalesHorizontalItemMain(homeStores),
            BestSellerVerticalItemMain(bestSellers)
        )
    }
}
