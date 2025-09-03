package org.example.project.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.example.project.db.DriverFactory

val androidModule = module {
    single { DriverFactory(androidContext()) }
}
