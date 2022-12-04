package com.alejandro.plana.pantallas

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alejandro.plana.pantallas.componentes.ColorTextorequisitos
import com.alejandro.plana.ui.theme.PlanA
import com.alejandro.plana.pantallas.componentes.ImageLogo
import com.alejandro.plana.pantallas.componentes.TextoRequisitos

@Composable
fun EmailLoginScreen(navController: NavController) {
    LazyColumn(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
        item { ImageLogo() }

        item {
            Alignment.BottomCenter; Box(
            Modifier
                .fillMaxSize()
        ) { EmailLogin(navController) }
        }
    }
}

@Composable
fun EmailLogin(navController: NavController) {
    var email: String by rememberSaveable { mutableStateOf("") }
    var password: String by rememberSaveable { mutableStateOf("") }
    var isLoginEnable by rememberSaveable { mutableStateOf(false) }

    Surface(
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        color = Color.White
    ) {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.size(16.dp))
            EmailLogin(email) {
                email = it
                isLoginEnable = EnableLogin(email, password)
            }
            TextoRequisitos(
                texto = "Un formato de email valido",
                modifier = Modifier.align(Alignment.Start),
                color = ColorTextorequisitos(email, 5)
            )
            PasswordLogin(password) {
                password = it
                isLoginEnable = EnableLogin(email, password)
            }
            TextoRequisitos(
                texto = "6 caracteres minimo",
                modifier = Modifier.align(Alignment.Start),
                color = ColorTextorequisitos(password, 2)
            )
            LoginButton(isLoginEnable, navController)
            MantenerIniciada()
        }
    }
}

@Composable
fun MantenerIniciada() {
    var state by rememberSaveable { mutableStateOf(false) }
    Row(
        Modifier
            .fillMaxWidth(),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Checkbox(
            checked = state,
            onCheckedChange = { state = !state },
            enabled = true,
            colors = CheckboxDefaults.colors(
                checkedColor = PlanA,
                uncheckedColor = Color(0xFFAD9D5F)
            )
        )

        Text(
            text = "Recordarme",
            style = TextStyle(color = Color.Gray, fontSize = 20.sp, fontWeight = FontWeight.Bold),
        )

    }

}

@Composable
fun LoginButton(loginEnable: Boolean, navController: NavController) {
    val context = LocalContext.current
    Button(
        onClick = {
            navController.navigate("home")
            Toast.makeText(
                context, "Has sido registrado correctamente", Toast.LENGTH_SHORT
            ).show()
        },
        enabled = loginEnable,
        shape = RoundedCornerShape(50.dp),
        modifier = Modifier
            .padding(10.dp)
            .wrapContentSize(Center)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFFECC1B),
            disabledBackgroundColor = Color(0xFFAD9D5F),
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(
            text = "Sign up",
            style = TextStyle(
                color = Color.White,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                fontSize = 25.sp
            )
        )
    }
}

fun EnableLogin(
    email: String, password: String,
): Boolean =
    Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
            password.length > 5

@Composable
fun PasswordLogin(password: String, onTextchanged: (String) -> Unit) {
    var passwordVisibility by remember { mutableStateOf(false) }
    TextField(
        value = password,
        onValueChange = { onTextchanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(50.dp),
        placeholder = {
            Text(
                text = "Password",
                color = Color.Gray,
                modifier = Modifier.padding(5.dp)
            )
        },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            backgroundColor = Color(0xFFE6E6E6),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            val imagen = if (passwordVisibility) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    imageVector = imagen,
                    contentDescription = "show password",
                    tint = Color(0xFFB2B2B2),
                    modifier = Modifier.padding(end = 10.dp)
                )
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}

@Composable
fun EmailLogin(email: String, onTextchanged: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextchanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(50.dp),
        placeholder = {
            Text(
                text = "Email",
                color = Color.Gray,
                modifier = Modifier.padding(5.dp)
            )
        },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            backgroundColor = Color(0xFFE6E6E6),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
    )
}