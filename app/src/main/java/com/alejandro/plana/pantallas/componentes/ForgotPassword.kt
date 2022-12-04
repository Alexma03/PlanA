package com.alejandro.plana.pantallas.componentes

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.alejandro.plana.ui.theme.BlueTwitter

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Contraseña Olvidada",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = BlueTwitter,
        modifier = modifier
    )
}