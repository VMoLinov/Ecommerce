package ru.test.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.test.core.Interactor
import ru.test.core.InteractorImpl
import ru.test.model.AppResourceProvider
import ru.test.model.ResourceProvider
import ru.test.network.NetworkSource
import ru.test.network.RetrofitBuilder
import javax.inject.Singleton

@Module
class MainModule {

    @Provides
    fun interactor(local: ResourceProvider, network: NetworkSource): Interactor =
        InteractorImpl(local, network)

    @Provides
    fun local(context: Context): ResourceProvider =
        AppResourceProvider(context)

    @Singleton
    @Provides
    fun network(): NetworkSource = RetrofitBuilder.buildRetrofit()
}
