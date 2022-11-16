package ru.test.ecommerce

import android.app.Application
import ru.test.ecommerce.di.AppComponent
import ru.test.ecommerce.di.DaggerAppComponent

class App : Application() {

    lateinit var applicationComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        applicationComponent = DaggerAppComponent
            .builder()
            .context(this)
            .build()
    }

    companion object {
        lateinit var instance: App
        fun getComponent() = instance.applicationComponent
    }
}