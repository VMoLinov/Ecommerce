package ru.test.ecommerce.repository

import kotlinx.coroutines.flow.Flow
import ru.test.ecommerce.repository.model.ResultDTO

interface NetworkSource {

    suspend fun getMainList(): Flow<ResultDTO>
}
