package ru.test.ecommerce.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.test.ecommerce.repository.model.ResultDTO

class NetworkSourceImpl : NetworkSource {

    override suspend fun getMainList(): Flow<ResultDTO> {
        return flow {emit(repo.getMainList()) }
    }

    companion object {
        private val repo = Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
}
