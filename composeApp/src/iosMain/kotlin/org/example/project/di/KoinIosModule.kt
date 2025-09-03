package org.example.project.di

import org.koin.dsl.module
import org.example.project.db.DriverFactory

val iosModule = module {
    single { DriverFactory() }
}
