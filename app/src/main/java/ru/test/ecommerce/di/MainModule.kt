package ru.test.ecommerce.di

import android.content.Context
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.test.ecommerce.interactor.Interactor
import ru.test.ecommerce.interactor.InteractorImpl
import ru.test.ecommerce.interactor.local.AppResourceProvider
import ru.test.ecommerce.interactor.local.ResourceProvider
import ru.test.ecommerce.interactor.network.Api
import ru.test.ecommerce.interactor.network.NetworkSource
import ru.test.ecommerce.interactor.network.NetworkSourceImpl

@Module
class MainModule {

    @Provides
    fun interactor(local: ResourceProvider, network: NetworkSource): Interactor =
        InteractorImpl(local, network)

    @Provides
    fun local(context: Context): ResourceProvider = AppResourceProvider(context)

    @Provides
    fun network(): NetworkSource {
        return NetworkSourceImpl(
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        )
    }

    companion object {
        const val BASE_URL = "https://run.mocky.io/v3/"
    }
}