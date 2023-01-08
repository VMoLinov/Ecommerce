package ru.test.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.test.domain.cartscreen.CartInteractor
import ru.test.domain.detailsscreen.DetailsInteractor
import ru.test.domain.interactor.Interactor
import ru.test.domain.interactor.InteractorImpl
import ru.test.domain.mainscreen.MainInteractor
import ru.test.repository.local.AppResourceProvider
import ru.test.repository.local.ResourceProvider
import ru.test.repository.network.NetworkSource
import ru.test.repository.network.RetrofitBuilder
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
