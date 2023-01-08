package ru.test.repository.network

import retrofit2.http.GET
import ru.test.repository.network.model.CartDTO
import ru.test.repository.network.model.DeviceDetailsDTO
import ru.test.repository.network.model.ResultDTO

interface ApiHolder {

    @GET("654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getMainList(): ResultDTO

    @GET("6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getDeviceDetails(): DeviceDetailsDTO

    @GET("53539a72-3c5f-4f30-bbb1-6ca10d42c149")
    suspend fun getCartDetails(): CartDTO
}
