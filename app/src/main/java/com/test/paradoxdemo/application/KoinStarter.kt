package com.test.paradoxdemo.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

class KoinStarter {

    private var koinApp: KoinApplication? = null

    fun start(application: Application) {
        koinApp = getOrCreateKoinApplication(application)
    }

    private fun getOrCreateKoinApplication(application: Application): KoinApplication {
        return GlobalContext.getOrNull()?.apply {
            loadKoinModules(getModules())
        } ?: startKoin(application)
    }

    private fun startKoin(application: Application): KoinApplication {
        return startKoin {
            androidContext(application)
            androidLogger(Level.DEBUG)
            modules(getModules())
        }
    }

    private fun getModules(): List<Module> = listOf()
}