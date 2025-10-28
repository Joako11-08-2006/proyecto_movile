package com.tecsup.mediturn.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.tecsup.mediturn.data.FakeRepository
import com.tecsup.mediturn.model.Doctor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var filteredDoctors by remember { mutableStateOf(FakeRepository.getAllDoctors()) }

    LaunchedEffect(searchQuery) {
        filteredDoctors = if (searchQuery.isBlank()) {
            FakeRepository.getAllDoctors()
        } else {
            FakeRepository.searchDoctors(searchQuery)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MediTurn") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF00695C),
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Buscar por nombre, especialidad o ciudad") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (filteredDoctors.isEmpty()) {
                Text(
                    text = "No se encontraron doctores.",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                LazyColumn {
                    items(filteredDoctors) { doctor ->
                        DoctorCard(doctor)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("agendarCita") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agendar nueva cita")
            }

            Button(
                onClick = { navController.navigate("misCitas") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver mis citas")
            }

            Button(
                onClick = { navController.navigate("perfil") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Perfil")
            }
        }
    }
}

@Composable
fun DoctorCard(doctor: Doctor) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F1F1))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = doctor.photoUrl),
                contentDescription = "Foto del doctor",
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 12.dp)
            )

            Column {
                Text(doctor.name, style = MaterialTheme.typography.titleMedium)
                Text(doctor.specialty, style = MaterialTheme.typography.bodyMedium)
                Text(doctor.city, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                if (doctor.teleconsulta) {
                    Text("Teleconsulta disponible", color = Color(0xFF00796B))
                }
            }
        }
    }
}
