package com.alejandro.plana

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alejandro.plana.navigation.Routes
import com.alejandro.plana.ui.theme.PlanATheme
import com.alejandro.plana.pantallas.EmailLoginScreen
import com.alejandro.plana.pantallas.HomeScreen
import com.alejandro.plana.pantallas.LoginScreen
import com.alejandro.plana.pantallas.RegistroScreen
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


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


