package ru.test.domain

import androidx.annotation.StringRes
import ru.test.domain.mainscreen.model.BestSeller
import ru.test.domain.mainscreen.model.HomeStore
import ru.test.domain.mainscreen.model.MainListItem

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
