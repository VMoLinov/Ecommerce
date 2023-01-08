package ru.test.repository.network

import kotlinx.coroutines.flow.Flow
import ru.test.repository.network.model.CartDTO
import ru.test.repository.network.model.DeviceDetailsDTO
import ru.test.repository.network.model.ResultDTO

interface NetworkSource {

    suspend fun getMainList(): Flow<ResultDTO>
    suspend fun getDeviceDetails(): Flow<DeviceDetailsDTO>
    suspend fun getCartDetails(): Flow<CartDTO>
}
