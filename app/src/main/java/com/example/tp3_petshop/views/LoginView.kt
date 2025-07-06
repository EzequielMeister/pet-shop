package com.example.tp3_petshop.views

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.launch



@Composable
fun LoginView(navController: NavController?) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val auth = FirebaseAuth.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Iniciar Sesión", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            // Aca estan los cambios realizados con firebase authentication respecto de la version anterior
            onClick = {
                loading = true
                scope.launch {
                    val cleanEmail = email.trim()
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(cleanEmail).matches()) {
                        Toast.makeText(context, "El email no tiene un formato válido", Toast.LENGTH_SHORT).show()
                        loading = false
                        return@launch
                    }
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task: Task<AuthResult> ->
                            loading = false
                            if (task.isSuccessful) {
                                Toast.makeText(context, "Login exitoso", Toast.LENGTH_SHORT).show()
                                navController?.navigate("homeScreen") {
                                    popUpTo("LoginView") { inclusive = true }
                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "Error: ${task.exception?.message ?: "Datos incorrectos"}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                }
            },
            enabled = email.isNotBlank() && password.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = if (loading) "Cargando..." else "Iniciar sesión")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = {
            navController?.navigate("register")
        }) {
            Text("¿No tenés cuenta? Registrate")
        }
    }
}