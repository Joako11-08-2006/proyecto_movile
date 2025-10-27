package com.tecsup.mediturn.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tecsup.mediturn.data.FakeRepository
import com.tecsup.mediturn.model.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendarCitaScreen(navController: NavController) {
    var motivo by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Agendar Cita") }) }
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
                    val doctor = FakeRepository.doctors.first()
                    val patient = Patient(1, "Juan Pérez", "juan@mail.com")
                    val slot = doctor.availableSlots.first()

                    val cita = Appointment(
                        id = FakeRepository.appointments.size + 1,
                        doctor = doctor,
                        patient = patient,
                        slot = slot,
                        reason = motivo
                    )

                    FakeRepository.addAppointment(cita)
                    navController.navigate("home")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar cita")
            }
        }
    }
}
