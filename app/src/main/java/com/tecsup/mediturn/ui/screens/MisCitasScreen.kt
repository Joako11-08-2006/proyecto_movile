package com.tecsup.mediturn.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tecsup.mediturn.data.FakeRepository
import com.tecsup.mediturn.model.Appointment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MisCitasScreen(navController: NavController) {
    val context = LocalContext.current
    var citas by remember { mutableStateOf(FakeRepository.getAppointments()) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Mis Citas") }) }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            items(citas.size) { index ->
                val cita = citas[index]
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(Modifier.padding(12.dp)) {
                        Text("Doctor: ${cita.doctor.name}", style = MaterialTheme.typography.titleMedium)
                        Text("Especialidad: ${cita.doctor.specialty}")
                        Text("Fecha: ${cita.slot.date} - ${cita.slot.time}")
                        Text("Motivo: ${cita.reason}")

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(onClick = {
                                FakeRepository.cancelAppointment(cita.id)
                                citas = FakeRepository.getAppointments()
                                Toast.makeText(context, "Cita cancelada", Toast.LENGTH_SHORT).show()
                            }) { Text("Cancelar") }

                            Button(onClick = {
                                FakeRepository.updateAppointment(
                                    id = cita.id,
                                    newDate = "2025-11-01",
                                    newTime = "09:00 AM"
                                )
                                citas = FakeRepository.getAppointments()
                                Toast.makeText(context, "Cita reprogramada", Toast.LENGTH_SHORT).show()
                            }) { Text("Reprogramar") }
                        }
                    }
                }
            }
        }
    }
}
