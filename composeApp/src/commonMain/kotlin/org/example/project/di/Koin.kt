package org.example.project.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.core.module.Module
import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import org.example.project.modules.dashboard.data.DashboardRepository
import org.example.project.modules.profile.ProfileViewModel
import org.example.project.modules.dashboard.DashboardViewModel
import org.example.project.db.CacheManager
import org.example.project.db.Database
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}, platformModules: List<Module> = emptyList()) {
    startKoin {
        appDeclaration()
        modules(appModule + platformModules)
    }
}

val appModule = module {
    single { Logger.withTag("MyLogger") }

    single<HttpClient> { provideHttpClient() }

    single { DashboardRepository(get(), get()) }
    factory { ProfileViewModel(get()) }
    factory { DashboardViewModel(repository = get(), logger = get()) }

    single { Database(get()) }
    single { CacheManager(get(),logger = get()) }
}