package com.test.paradoxdemo.application

import android.app.Application
import com.test.paradoxdemo.dataBase.di.roomModule
import com.test.paradoxdemo.network.di.retrofitModule
import com.test.paradoxdemo.ui.favorite.di.favoriteFragmentModule
import com.test.paradoxdemo.ui.main.di.mainFragmentModule
import com.test.paradoxdemo.ui.product.di.deatiledFragmentModule
import com.test.paradoxdemo.ui.productsList.di.productsFragmentModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

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

    private fun getModules() = listOf(retrofitModule,mainFragmentModule, productsFragmentModule,roomModule,deatiledFragmentModule,favoriteFragmentModule)
}