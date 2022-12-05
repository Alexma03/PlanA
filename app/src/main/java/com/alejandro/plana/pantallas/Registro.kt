package com.alejandro.plana.pantallas

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.alejandro.plana.navigation.Routes
import com.alejandro.plana.navigation.Routes.*
import com.alejandro.plana.objeto.Persona
import com.alejandro.plana.pantallas.componentes.ColorTextorequisitos
import com.alejandro.plana.pantallas.componentes.ImageLogo
import com.alejandro.plana.pantallas.componentes.TextoRequisitos
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.alejandro.plana.ui.theme.BlueTwitter


@Composable
fun RegistroScreen(navController: NavHostController) {
    LazyColumn(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
        item { Alignment.Start;ImageLogo() }

        item {
            Alignment.BottomCenter; Box(
            Modifier
                .fillMaxSize()
        ) { RegistroUsuario(navController) }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RegistroUsuario(navController: NavHostController) {
    var name: String by rememberSaveable { mutableStateOf("") }
    var email: String by rememberSaveable { mutableStateOf("") }
    var password: String by rememberSaveable { mutableStateOf("") }
    var isSingUpEnable by rememberSaveable { mutableStateOf(false) }
    var repeatPaassword by rememberSaveable { mutableStateOf("") }
    var codigoPostal by rememberSaveable { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val bringIntoViewRequester = BringIntoViewRequester()

    var usuario = Persona("", "", "", "")
    usuario.name = name
    usuario.email = email
    usuario.password = password
    usuario.codigoPostal = codigoPostal

    Surface(
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        color = Color.White
    ) {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.size(16.dp))
            Name(name) { name = it }
            TextoRequisitos(
                texto = "1 letra en mayuscula minimo",
                Modifier.align(Alignment.Start),
                ColorTextorequisitos(name)
            )
            EmailRegister(email) {
                email = it
                isSingUpEnable = EnableSingUp(email, password, name, repeatPaassword, codigoPostal)
            }
            TextoRequisitos(
                texto = "Un formato de email valido",
                Modifier.align(Alignment.Start),
                ColorTextorequisitos(email, 5)
            )
            PasswordRegister(
                password,
                bringIntoViewRequester,
                coroutineScope,
                focusManager
            ) {
                password = it
                isSingUpEnable = EnableSingUp(email, password, name, repeatPaassword, codigoPostal)
            }
            TextoRequisitos(
                texto = "6 caracteres minimo",
                Modifier.align(Alignment.Start),
                ColorTextorequisitos(password, 2)
            )
            RepeatPassword(
                repeatPaassword,
                bringIntoViewRequester,
                coroutineScope,
                focusManager
            ) {
                repeatPaassword = it
                isSingUpEnable = EnableSingUp(email, password, name, repeatPaassword, codigoPostal)
            }
            TextoRequisitos(
                texto = "Misma contraseña",
                Modifier.align(Alignment.Start),
                ColorTextorequisitos(repeatPaassword, 3, password)
            )
            CodigoPostal(
                codigoPostal,
                bringIntoViewRequester,
                coroutineScope,
                focusManager
            ) { codigoPostal = it }
            TextoRequisitos(
                texto = "5 digitos maximo",
                Modifier.align(Alignment.Start),
                ColorTextorequisitos(codigoPostal, 4)
            )
            SignUpButton(
                isSingUpEnable,
                usuario.password,
                usuario.name,
                repeatPaassword,
                navController,
                bringIntoViewRequester
            )
            Login(navController = navController)
        }
    }

}

fun EnableSingUp(
    email: String,
    password: String,
    name: String,
    repeatPassword: String,
    codigoPostal: String
): Boolean =
    Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 5 &&
            name.isNotEmpty() && repeatPassword == password

            //&& codigoPostal.length > 4 (conseguir que funcione

@Composable
fun Login(navController: NavHostController) {
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
                text = "¿Tienes cuenta?",
                fontSize = 15.sp,
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Log in.",
                Modifier
                    .padding(horizontal = 8.dp)
                    .clickable(onClick = { navController.popBackStack() }),
                fontSize = 15.sp,
                color = BlueTwitter,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Spacer(modifier = Modifier.size(24.dp))
    }


}





@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CodigoPostal(
    codigoPostal: String,
    bringIntoViewRequester: BringIntoViewRequester,
    coroutineScope: CoroutineScope, focusManager: FocusManager,
    onTextchanged: (String) -> Unit
) {
    TextField(
        value = codigoPostal, onValueChange = { onTextchanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .onFocusEvent { event ->
                if (event.isFocused) {
                    coroutineScope.launch {
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            },
        shape = RoundedCornerShape(50.dp),
        placeholder = {
            Text(
                text = "Codigo Postal",
                color = Color.Gray,
                modifier = Modifier.padding(5.dp)
            )
        },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        ),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            backgroundColor = Color(0xFFE6E6E6),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SignUpButton(
    SignUpEnable: Boolean,
    password: String,
    name: String,
    repeatPassword: String,
    navController: NavHostController,
    bringIntoViewRequester: BringIntoViewRequester
) {
    val context = LocalContext.current
    Button(
        onClick = {
            if (password != repeatPassword) {
                Toast.makeText(
                    context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT
                ).show()
            } else {
                navController.navigate(EmailLogin.route)
                Toast.makeText(
                    context, "$name has sido registrado correctamente", Toast.LENGTH_SHORT
                ).show()
            }
        },
        enabled = SignUpEnable,
        shape = RoundedCornerShape(50.dp),
        modifier = Modifier
            .padding(10.dp)
            .wrapContentSize(Alignment.Center)
            .fillMaxWidth()
            .bringIntoViewRequester(bringIntoViewRequester),
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

@Composable
fun Name(name: String, onTextchanged: (String) -> Unit) {
    TextField(
        value = name, onValueChange = { onTextchanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(50.dp),
        placeholder = {
            Text(
                text = "Name",
                color = Color.Gray,
                modifier = Modifier.padding(5.dp)
            )
        },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            backgroundColor = Color(0xFFE6E6E6),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PasswordRegister(
    password: String,
    bringIntoViewRequester: BringIntoViewRequester,
    coroutineScope: CoroutineScope,
    focusManager: FocusManager,
    onTextchanged: (String) -> Unit
) {
    var passwordVisibility by remember { mutableStateOf(false) }
    TextField(
        value = password,
        onValueChange = { onTextchanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .onFocusEvent { event ->
                if (event.isFocused) {
                    coroutineScope.launch {
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            },
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
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RepeatPassword(
    password: String,
    bringIntoViewRequester: BringIntoViewRequester,
    coroutineScope: CoroutineScope, focusManager: FocusManager,
    onTextchanged: (String) -> Unit
) {
    var passwordVisibility by remember { mutableStateOf(false) }
    TextField(
        value = password,
        onValueChange = { onTextchanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .onFocusEvent { event ->
                if (event.isFocused) {
                    coroutineScope.launch {
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            },
        shape = RoundedCornerShape(50.dp),
        placeholder = {
            Text(
                text = "Repeat Password",
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
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
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
fun EmailRegister(email: String, onTextchanged: (String) -> Unit) {
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


