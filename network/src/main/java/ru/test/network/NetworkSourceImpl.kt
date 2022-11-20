package ru.test.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.test.network.model.CartDTO
import ru.test.network.model.DeviceDetailsDTO
import ru.test.network.model.ResultDTO
import javax.inject.Inject

class NetworkSourceImpl @Inject constructor(
    private val apiHolder: ApiHolder
) : NetworkSource {

    override suspend fun getMainList(): Flow<ResultDTO> {
        return flow { emit(apiHolder.getMainList()) }
    }

    override suspend fun getDeviceDetails(): Flow<DeviceDetailsDTO> {
        return flow { emit(apiHolder.getDeviceDetails()) }
    }

    override suspend fun getCartDetails(): Flow<CartDTO> {
        return flow { emit(apiHolder.getCartDetails()) }
    }
}
