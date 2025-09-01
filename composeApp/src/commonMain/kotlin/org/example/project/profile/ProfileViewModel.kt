package org.example.project.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.project.dashboard.data.model.User

class ProfileViewModel(private val logger: Logger) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode: StateFlow<Boolean> = _isDarkMode


    init {
        logger.d("Initializing ProfileViewModel")
        fetchUser()
    }

    private fun fetchUser() {
        viewModelScope.launch {
            val user = User(
                "John Doe",
                "john.doe@example.com",
                bio = "",
                version = 1.0,
                profilePicture = "https://media.istockphoto.com/id/2063799507/photo/business-portrait-and-black-man-in-city-outdoor-for-career-or-job-of-businessman-face.jpg?s=2048x2048&w=is&k=20&c=ekUFjW0r-y8F13ldMuxyQWZ6OxtWoAeJGIXhPb0f5YI=",
                id = "1"
            )
            _user.value = user
            logger.i("Fetched user: $user")
        }
    }
    fun toggleDarkMode() {
        _isDarkMode.value = !_isDarkMode.value
    }
}
