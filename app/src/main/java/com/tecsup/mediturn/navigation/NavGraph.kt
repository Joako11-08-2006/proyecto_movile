package com.tecsup.mediturn.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tecsup.mediturn.ui.screens.*

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "login" // o "login" si empieza por login
    ) {
        composable("login") { Login(navController) }
        composable("home") { HomeScreen(navController) }
        composable("agendarCita") { AgendarCitaScreen(navController) }
        composable("misCitas") { MisCitasScreen(navController) }
        composable("perfil") { ProfileScreen(navController) }
    }
}
