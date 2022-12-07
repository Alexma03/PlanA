package com.alejandro.plana.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alejandro.plana.pantallas.EmailLoginScreen
import com.alejandro.plana.pantallas.HomeScreen
import com.alejandro.plana.pantallas.LoginScreen
import com.alejandro.plana.pantallas.RegistroScreen

@Composable
fun Navegacion() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = Routes.Login.route) {
        composable(Routes.Login.route) { LoginScreen(navigationController) }
        composable(Routes.Registro.route) { RegistroScreen(navigationController) }
        composable(Routes.Home.route) { HomeScreen(navigationController) }
        composable(Routes.EmailLogin.route) { EmailLoginScreen(navigationController) }

    }

}