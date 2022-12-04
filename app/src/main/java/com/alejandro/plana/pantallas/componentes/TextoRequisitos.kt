package com.alejandro.plana.pantallas.componentes

import android.util.Patterns
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextoRequisitos(texto: String, modifier: Modifier, color: Color) {
    Text(
        text = "* $texto",
        style = TextStyle(
            color = color,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp
        ),
        modifier = modifier.padding(top = 2.dp, bottom = 5.dp, start = 20.dp)
    )
}

fun ColorTextorequisitos(texto: String, condicion: Int = 1, repeatPassword: String = ""): Color {
    if (condicion == 1) {
        if (texto.isBlank()) {
            return Color.Red
        } else {
            return Color.Gray
        }
    } else if (condicion == 2) {
        if (texto.length < 6) {
            return Color.Red
        } else {
            return Color.Gray
        }
    } else if (condicion == 3) {
        if (texto == repeatPassword){
            return Color.Gray
        } else {
            return Color.Red
        }
    } else if (condicion == 4) {
        if (texto.length < 5) {
            return Color.Red
        } else {
            return Color.Gray
        }
    } else {
        if (Patterns.EMAIL_ADDRESS.matcher(texto).matches()) {
            return Color.Gray
        } else {
            return Color.Red
        }
    }
}