package com.alejandro.plana.pantallas.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.alejandro.plana.R

@Composable
fun ImageLogo() {
    Image(
        painter = painterResource(id = R.drawable.imagen_blanca),
        contentDescription = "PlanA",
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 50.dp)
    )
}