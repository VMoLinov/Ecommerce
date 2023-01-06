package ru.test.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.test.domain.AppResourceProvider
import ru.test.domain.ResourceProvider
import ru.test.domain.cartscreen.CartInteractor
import ru.test.domain.detailsscreen.DetailsInteractor
import ru.test.domain.interactor.Interactor
import ru.test.domain.interactor.InteractorImpl
import ru.test.domain.mainscreen.MainInteractor
import ru.test.network.NetworkSource
import ru.test.network.RetrofitBuilder
import javax.inject.Singleton

@Module
class MainModule {

    @Singleton
    @Provides
    fun interactor(local: ResourceProvider, network: NetworkSource): Interactor {
        return InteractorImpl(
            MainInteractor(local, network),
            DetailsInteractor(network),
            CartInteractor(network)
        )
    }

    @Singleton
    @Provides
    fun local(context: Context): ResourceProvider =
        AppResourceProvider(context)

    @Singleton
    @Provides
    fun network(): NetworkSource = RetrofitBuilder.buildRetrofit()
}
