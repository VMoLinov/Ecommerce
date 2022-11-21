package ru.test.core.di

import android.app.Application

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