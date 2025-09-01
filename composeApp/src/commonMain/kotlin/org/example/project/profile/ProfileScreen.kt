package org.example.project.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
) {
    val user by viewModel.user.collectAsState()
    val isDarkMode by viewModel.isDarkMode.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Profile", fontWeight = FontWeight.Light, fontSize = 22.sp) }) },
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(it)
        ) {
            Row( modifier = Modifier.fillMaxWidth().padding(10.dp)) {
                user?.let {
                    UserView(user = it)
                }
            }
            Column (modifier = Modifier
                .padding(20.dp,20.dp)){
            Text("Settings", fontWeight = FontWeight.Light, fontSize = 22.sp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp,20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Dark Mode")
                Switch(
                    checked = isDarkMode,
                    onCheckedChange = { viewModel.toggleDarkMode() }
                )
            }}
        }
    }
}
