package ru.test.ecommerce.interactor.network

import kotlinx.coroutines.flow.Flow
import ru.test.ecommerce.interactor.network.model.ResultDTO

interface NetworkSource {

    suspend fun getMainList(): Flow<ResultDTO>
}