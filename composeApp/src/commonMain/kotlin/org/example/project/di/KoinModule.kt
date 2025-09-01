package org.example.project.di

import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import org.example.project.modules.dashboard.data.DashboardRepository
import org.example.project.modules.dashboard.DashboardViewModel
import org.koin.dsl.module
import org.example.project.modules.profile.ProfileViewModel
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val client = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true   // ignores extra fields not in your User class
        })
    }
}

val appModule = module {
    single { Logger.withTag("MyLogger") }

    single { DashboardRepository(client) }
    factory { ProfileViewModel(get()) }

    factory { DashboardViewModel(repository = get(), logger = get()) }
}
