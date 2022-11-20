package ru.test.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    fun buildRetrofit(): NetworkSource {
        return NetworkSourceImpl(
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiHolder::class.java)
        )
    }

    private const val BASE_URL = "https://run.mocky.io/v3/"
}
