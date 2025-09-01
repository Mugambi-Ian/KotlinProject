package org.example.project

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.example.project.dashboard.presentation.DashboardViewModel
import org.example.project.di.initKoin
import org.example.project.navigation.AppNavigation
import org.example.project.profile.ProfileViewModel
import org.example.project.theme.AppTheme
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
fun App() {
    KoinApplication(application = { initKoin() }) {
        val profileViewModel: ProfileViewModel = koinInject()
        val dashboardViewModel: DashboardViewModel = koinInject()
        val isDarkMode by profileViewModel.isDarkMode.collectAsState()

        AppTheme(darkTheme = isDarkMode) {
            AppNavigation(profileViewModel,dashboardViewModel)
        }
    }
}
