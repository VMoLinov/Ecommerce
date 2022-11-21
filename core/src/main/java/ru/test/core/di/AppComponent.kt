package ru.test.core.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.test.core.AppViewModelFactory
import javax.inject.Singleton

@Singleton
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
