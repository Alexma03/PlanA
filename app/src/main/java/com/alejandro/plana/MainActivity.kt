package com.alejandro.plana

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                    NavHost(navController = navigationController, startDestination = "login") {
                        composable("login") { LoginScreen(navigationController) }
                        composable("registro") { RegistroScreen(navigationController) }
                        composable("home") { HomeScreen(navigationController) }
                        composable("email") { EmailLoginScreen(navigationController) }
                    }
                }
            }
        }
        ViewCompat. setOnApplyWindowInsetsListener(findViewById(android.R.id.content)){ view, insets ->
            val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            view.updatePadding((bottom))

            insets

        }
    }
}

