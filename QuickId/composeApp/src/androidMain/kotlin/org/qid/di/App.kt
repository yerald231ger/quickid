package org.qid.di

import android.app.Application

class App : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppContainer(QuickIdDatabaseFactory(this))
    }
}