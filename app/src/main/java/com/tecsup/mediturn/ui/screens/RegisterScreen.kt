package com.tecsup.mediturn.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RegisterScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Home") }
            )
        }
    ) { padding ->
        Button(
            onClick = { /* luego navegaremos a login */ },
            modifier = Modifier.padding(padding)
        ) {
            Text("Volver al Login")
        }
    }
}
