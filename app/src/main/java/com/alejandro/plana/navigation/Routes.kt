package com.alejandro.plana.navigation

sealed class Routes(val route: String) {
    object Login:Routes("login")
    object Registro:Routes("registro")
    object Home:Routes("home")
    object EmailLogin:Routes("email")



}