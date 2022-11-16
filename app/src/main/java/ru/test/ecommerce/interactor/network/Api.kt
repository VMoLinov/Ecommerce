package ru.test.ecommerce.interactor.network

import retrofit2.http.GET
import ru.test.ecommerce.interactor.network.model.ResultDTO

interface Api {

    @GET("654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getMainList(): ResultDTO
}
