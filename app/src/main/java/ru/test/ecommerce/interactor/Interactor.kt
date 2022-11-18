package ru.test.ecommerce.interactor

import kotlinx.coroutines.flow.Flow
import ru.test.ecommerce.ui.cart.Cart
import ru.test.ecommerce.ui.details.DeviceDetails
import ru.test.ecommerce.ui.main.adapter.MainListItem

interface Interactor {

    suspend fun getMainListData(): Flow<List<MainListItem>>
    suspend fun getDeviceDetails(): Flow<DeviceDetails>
    suspend fun getCartDetails(): Flow<Cart>
    suspend fun getBrands(): List<String>
    suspend fun getPrices(): List<String>
    suspend fun getSizes(): List<String>
    suspend fun deleteItemFromCart(id: Long): Cart?
    suspend fun plusPosition(id: Long): Cart?
    suspend fun minusPosition(id: Long): Cart?
}
