package org.example.project.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import compose.icons.FeatherIcons
import compose.icons.feathericons.Home
import compose.icons.feathericons.User
import org.example.project.dashboard.presentation.DashboardScreen
import org.example.project.dashboard.presentation.DashboardViewModel
import org.example.project.profile.ProfileScreen
import org.example.project.profile.ProfileViewModel

@Composable
fun AppNavigation(profileViewModel: ProfileViewModel,dashboardViewModel: DashboardViewModel) {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Dashboard) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(FeatherIcons.Home, contentDescription = "Dashboard") },
                    label = { Text("Dashboard") },
                    selected = currentScreen == Screen.Dashboard,
                    onClick = { currentScreen = Screen.Dashboard }
                )
                NavigationBarItem(
                    icon = { Icon(FeatherIcons.User, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = currentScreen == Screen.Profile,
                    onClick = { currentScreen = Screen.Profile }
                )
            }
        }
    ) { innerPadding ->
        when (currentScreen) {
            Screen.Dashboard -> DashboardScreen(dashboardViewModel)
            Screen.Profile -> ProfileScreen(profileViewModel)
        }
    }
}

sealed class Screen {
    data object Dashboard : Screen()
    data object Profile : Screen()
}
