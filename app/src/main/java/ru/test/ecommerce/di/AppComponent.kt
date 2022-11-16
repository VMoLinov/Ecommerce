package ru.test.ecommerce.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.test.ecommerce.utils.AppViewModelFactory

@Component(modules = [MainModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun viewModel(): AppViewModelFactory
}
