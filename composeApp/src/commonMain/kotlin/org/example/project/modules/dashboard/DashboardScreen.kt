package org.example.project.modules.dashboard

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(viewModel: DashboardViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    "Dashboard",
                    fontWeight = FontWeight.Light,
                    fontSize = 22.sp
                )
            })
        },
    ) {
        when (val state = uiState) {
            is DashboardUiState.Loading -> {
                SkeletonLoader(
                    modifier = Modifier.fillMaxWidth().padding(it)
                )
            }

            is DashboardUiState.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth().padding(it)
                ) {
                    items(state.users) { user ->
                        DashboardItem(user)
                    }
                }
            }

            is DashboardUiState.Error -> {
                Text(
                    text = state.message, modifier = Modifier.fillMaxWidth().padding(it)
                )
            }
        }
    }
}
