package com.alejandro.plana.pantallas.componentes.botones

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alejandro.plana.R
import com.alejandro.plana.ui.theme.BlueGoogle

@Composable
fun Google(navController: NavController) {
    Card(
        elevation = 15.dp,
        shape = RoundedCornerShape(50.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(10.dp)
            .wrapContentSize(Alignment.Center)
            .fillMaxWidth()
            .clickable(onClick = { navController.navigate("home") })

    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight(Alignment.CenterVertically)
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.kisspng_google_logo_g_suite_google_5ab6f1f10f4d49_7230064015219389290627),
                contentDescription = "Google Logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(50.dp)
                    .padding(start = 5.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Box(
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(
                    text = "Continuar con Google",
                    style = TextStyle(
                        color = BlueGoogle,
                        fontFamily =  FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                )
            }
        }
    }
}