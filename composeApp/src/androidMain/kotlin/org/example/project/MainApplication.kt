package org.example.project

import android.app.Application
import org.example.project.di.androidModule
import org.example.project.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(appDeclaration = {
            androidContext(this@MainApplication)
            androidLogger()
        }, platformModules = listOf(androidModule))
    }
}