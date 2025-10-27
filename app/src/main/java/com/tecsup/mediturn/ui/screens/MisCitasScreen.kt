package com.tecsup.mediturn.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tecsup.mediturn.data.FakeRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MisCitasScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Mis Citas") })
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            items(FakeRepository.appointments.size) { index ->
                val cita = FakeRepository.appointments[index]
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Doctor: ${cita.doctor.name}", style = MaterialTheme.typography.titleMedium)
                        Text("Especialidad: ${cita.doctor.specialty}")
                        Text("Fecha: ${cita.slot.date} — ${cita.slot.time}")
                        Text("Motivo: ${cita.reason}")
                    }
                }
            }
        }
    }
}
