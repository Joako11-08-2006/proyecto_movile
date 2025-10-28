package com.tecsup.mediturn.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tecsup.mediturn.data.FakeRepository
import com.tecsup.mediturn.model.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendarCitaScreen(navController: NavController) {
    val context = LocalContext.current
    var motivo by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Agendar Cita") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(24.dp)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = motivo,
                onValueChange = { motivo = it },
                label = { Text("Motivo de la cita") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (motivo.isBlank()) {
                        Toast.makeText(context, "Ingrese un motivo válido", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    // Obtener el primer doctor y horario disponible
                    val doctor = FakeRepository.doctors.first()
                    val slot = doctor.availableSlots.first()

                    // Crear cita
                    val cita = Appointment(
                        id = FakeRepository.getAppointments().size + 1,
                        doctor = doctor,
                        slot = slot,
                        reason = motivo
                    )

                    // Guardar cita
                    FakeRepository.addAppointment(cita)
                    Toast.makeText(context, "Cita registrada correctamente", Toast.LENGTH_SHORT).show()

                    // Regresar al Home
                    navController.navigate("home")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar cita")
            }
        }
    }
}
