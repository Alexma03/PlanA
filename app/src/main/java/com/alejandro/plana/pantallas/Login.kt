package com.alejandro.plana.pantallas

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.alejandro.plana.pantallas.componentes.botones.Email
import com.alejandro.plana.pantallas.componentes.botones.Facebook
import com.alejandro.plana.pantallas.componentes.botones.Google
import com.alejandro.plana.pantallas.componentes.botones.Twitter
import com.alejandro.plana.pantallas.componentes.ImageLogo
import com.alejandro.plana.ui.theme.BlueTwitter

@Composable
fun LoginScreen(navController: NavHostController) {
    LazyColumn(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
        item { ImageLogo() }

        item { Arrangement.End; Box(Modifier.fillMaxSize()) { LoginBotones(navController) } }
    }
}

@Composable
fun LoginBotones(navController: NavHostController) {
    Surface(
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        color = Color.White,

        ) {
        Column {
            Spacer(Modifier.height(10.dp))
            Google(navController)
            Twitter(navController)
            Facebook(navController)
            Email(navController)

            SignUp(navController)
        }

    }


}

@Composable
fun SignUp(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Divider(
            Modifier
                .padding(24.dp)
                .background(Color.Gray)
                .height(2.dp)
                .fillMaxWidth()

        )

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = "¿No tienes cuenta?",
                fontSize = 15.sp,
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Sign up.",
                Modifier
                    .padding(horizontal = 8.dp)
                    .clickable(onClick = { navController.navigate("registro") }),
                fontSize = 15.sp,
                color = BlueTwitter,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Spacer(modifier = Modifier.size(24.dp))
    }


}


