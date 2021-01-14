package com.test.paradoxdemo.application

import android.app.Application

@Suppress("unused")
class ParadoxApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() = KoinStarter().start(this)
}