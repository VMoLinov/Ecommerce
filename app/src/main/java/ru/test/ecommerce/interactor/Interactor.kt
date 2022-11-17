package ru.test.ecommerce.interactor

import kotlinx.coroutines.flow.Flow
import ru.test.ecommerce.ui.main.adapter.MainListItem

interface Interactor {

    suspend fun getMainListData(): Flow<List<MainListItem>>
    suspend fun getBrands(): List<String>
    suspend fun getPrices(): List<String>
    suspend fun getSizes(): List<String>
}
