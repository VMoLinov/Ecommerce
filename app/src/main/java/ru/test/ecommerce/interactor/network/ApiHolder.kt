package ru.test.ecommerce.interactor.network

import retrofit2.http.GET
import ru.test.ecommerce.interactor.network.model.DeviceDetailsDTO
import ru.test.ecommerce.interactor.network.model.ResultDTO

interface ApiHolder {

    @GET("654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getMainList(): ResultDTO

    @GET("6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getDeviceDetails(): DeviceDetailsDTO
}
