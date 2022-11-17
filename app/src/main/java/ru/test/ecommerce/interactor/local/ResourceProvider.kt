package ru.test.ecommerce.interactor.local

import androidx.annotation.StringRes
import ru.test.ecommerce.interactor.local.model.BestSeller
import ru.test.ecommerce.interactor.local.model.HomeStore
import ru.test.ecommerce.ui.main.adapter.MainListItem

interface ResourceProvider {

    fun string(@StringRes id: Int): String

    fun wrapDataWithConstants(
        homeStores: List<HomeStore>,
        bestSellers: List<BestSeller>
    ): List<MainListItem>

    fun getBrands(): List<String>

    fun getPrices(): List<String>

    fun getSizes(): List<String>
}
