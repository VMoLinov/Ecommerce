package ru.test.ecommerce.interactor.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.test.ecommerce.interactor.network.model.ResultDTO
import javax.inject.Inject

class NetworkSourceImpl @Inject constructor(
    private val api: Api
) : NetworkSource {

    override suspend fun getMainList(): Flow<ResultDTO> {
        return flow { emit(api.getMainList()) }
    }
}
