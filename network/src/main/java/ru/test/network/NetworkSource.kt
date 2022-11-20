package ru.test.network

import kotlinx.coroutines.flow.Flow
import ru.test.network.model.CartDTO
import ru.test.network.model.DeviceDetailsDTO
import ru.test.network.model.ResultDTO

interface NetworkSource {

    suspend fun getMainList(): Flow<ResultDTO>
    suspend fun getDeviceDetails(): Flow<DeviceDetailsDTO>
    suspend fun getCartDetails(): Flow<CartDTO>
}
