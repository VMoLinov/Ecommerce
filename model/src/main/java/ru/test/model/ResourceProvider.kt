package ru.test.model

import androidx.annotation.StringRes
import ru.test.model.model.BestSeller
import ru.test.model.model.HomeStore

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
