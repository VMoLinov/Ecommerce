package ru.test.ecommerce.repository

import retrofit2.http.GET
import ru.test.ecommerce.repository.model.ResultDTO

interface Api {

    @GET("654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getMainList(): ResultDTO
}
