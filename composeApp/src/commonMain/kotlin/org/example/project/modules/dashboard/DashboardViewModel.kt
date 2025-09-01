package org.example.project.modules.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.modules.dashboard.data.DashboardRepository
import org.example.project.modules.dashboard.data.model.User

class DashboardViewModel(private val repository: DashboardRepository,private val logger: Logger) : ViewModel() {

    private val _uiState = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                val users = repository.getUsers()
                _uiState.value = DashboardUiState.Success(users)
            } catch (e: Exception) {
                logger.e(e.message?:"error",e)
                _uiState.value = DashboardUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed interface DashboardUiState {
    data object Loading : DashboardUiState
    data class Success(val users: List<User>) : DashboardUiState
    data class Error(val message: String) : DashboardUiState
}
