package com.pinheiro.marvelhqs.presentation

import android.app.Application
import com.pinheiro.marvelhqs.di.testAppModule
import org.koin.core.context.startKoin

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(testAppModule )
        }
    }
}