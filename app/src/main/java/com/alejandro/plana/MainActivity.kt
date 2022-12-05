package com.alejandro.plana

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alejandro.plana.navigation.Routes
import com.alejandro.plana.ui.theme.PlanATheme
import com.alejandro.plana.pantallas.EmailLoginScreen
import com.alejandro.plana.pantallas.HomeScreen
import com.alejandro.plana.pantallas.LoginScreen
import com.alejandro.plana.pantallas.RegistroScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlanATheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(navController = navigationController, startDestination = Routes.Login.route) {
                        composable(Routes.Login.route) { LoginScreen(navigationController) }
                        composable(Routes.Registro.route) { RegistroScreen(navigationController) }
                        composable(Routes.Home.route) { HomeScreen(navigationController) }
                        composable(Routes.EmailLogin.route) { EmailLoginScreen(navigationController) }
                    }
                }
            }
        }
    }
}

