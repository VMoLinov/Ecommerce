package ru.test.ecommerce.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.test.ecommerce.interactor.Interactor
import ru.test.ecommerce.interactor.InteractorImpl
import ru.test.ecommerce.interactor.local.AppResourceProvider
import ru.test.ecommerce.interactor.local.ResourceProvider
import ru.test.network.NetworkSource
import ru.test.network.RetrofitBuilder
import javax.inject.Singleton

@Module
class MainModule {

    @Provides
    fun interactor(local: ResourceProvider, network: NetworkSource): Interactor =
        InteractorImpl(local, network)

    @Provides
    fun local(context: Context): ResourceProvider = AppResourceProvider(context)

    @Singleton
    @Provides
    fun network(): NetworkSource = RetrofitBuilder.buildRetrofit()
}
