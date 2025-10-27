package com.tecsup.mediturn.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.tecsup.mediturn.data.FakeRepository
import com.tecsup.mediturn.model.Doctor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    // Estado del texto de búsqueda
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    // Lista de doctores mostrada (filtrada)
    var filteredDoctors by remember { mutableStateOf(FakeRepository.doctors) }

    // Cada vez que cambia la búsqueda, filtramos
    LaunchedEffect(searchQuery.text) {
        val query = searchQuery.text
        filteredDoctors = if (query.isEmpty()) {
            FakeRepository.doctors
        } else {
            FakeRepository.doctors.filter {
                it.name.contains(query, ignoreCase = true) ||
                        it.specialty.contains(query, ignoreCase = true)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MediTurn") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0077B6), titleContentColor = Color.White)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            // Campo de búsqueda
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Buscar por nombre o especialidad") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(android.R.drawable.ic_menu_search),
                        contentDescription = "Buscar"
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de doctores filtrada
            LazyColumn {
                items(filteredDoctors.size) { index ->
                    DoctorCard(filteredDoctors[index])
                }
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
                painter = rememberAsyncImagePainter(doctor.photoUrl),
                contentDescription = "Foto del doctor",
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 12.dp)
            )
            Column {
                Text(text = doctor.name, style = MaterialTheme.typography.titleMedium)
                Text(text = doctor.specialty, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }
        }
    }
}
