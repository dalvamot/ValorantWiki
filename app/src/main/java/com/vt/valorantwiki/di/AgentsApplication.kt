package com.vt.valorantwiki.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AgentsApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AgentsApplication)
            modules(listOf(networkModule, viewModelModule))
        }
    }
}