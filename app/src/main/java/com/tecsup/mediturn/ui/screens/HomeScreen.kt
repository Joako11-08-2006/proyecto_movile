package com.tecsup.mediturn.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Home") }
            )
        }
    ) { padding ->
        Text(
            text = "Bienvenido a MediTurn 🏥",
            modifier = Modifier.padding(padding)
        )
    }
}
