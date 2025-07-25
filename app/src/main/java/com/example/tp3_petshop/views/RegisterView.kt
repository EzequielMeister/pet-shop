package com.example.tp3_petshop.views

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tp3_petshop.components.ButtonAuthComp
import com.example.tp3_petshop.components.FormAuth
import com.example.tp3_petshop.ui.theme.TP3PETSHOPTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun RegisterView(navController: NavController? = null) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var agreedToTerms by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    var loading by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val auth = FirebaseAuth.getInstance() // Inicialización de FirebaseAuth.

    val isButtonEnabled = fullName.isNotBlank() && email.isNotBlank() && password.isNotBlank() && agreedToTerms

    suspend fun getNextUserId(): Int {
        val db = Firebase.firestore
        val snapshot = db.collection("user_mappings").get().await()
        val maxId = snapshot.documents.mapNotNull { it.getLong("userId") }.maxOrNull() ?: 0L
        return (maxId + 1).toInt()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Column {
            Text("Create New \nAccount", fontSize = 44.sp, lineHeight = 50.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Water is life. Water is a basic human need. In various lines of life, humans need water.",
                color = Color.Gray,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            FormAuth(
                value = fullName,
                onValueChange = {fullName = it},
                placeholder = "Full Name",
                keyboard = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Spacer(modifier = Modifier.height(20.dp))
            FormAuth(
                value = email,
                onValueChange = {email = it},
                placeholder = "Email",
                keyboard = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Spacer(modifier = Modifier.height(20.dp))
            FormAuth(
                value = password,
                onValueChange = {password = it},
                placeholder = "Password",
                keyboard = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = agreedToTerms,
                    onCheckedChange = { agreedToTerms = it }
                )
                val annotatedString = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Gray)) {
                        append("I Agree to the ")
                    }
                    withStyle(style = SpanStyle(color = Color(0xFF6C63FF), fontWeight = FontWeight.Bold)) {
                        append("Terms of Service")
                    }
                    withStyle(style = SpanStyle(color = Color.Gray)) {
                        append(" and ")
                    }
                    withStyle(style = SpanStyle(color = Color(0xFF6C63FF), fontWeight = FontWeight.Bold)) {
                        append("Privacy Policy")
                    }
                }
                Text(
                    text = annotatedString,
                    modifier = Modifier.clickable { }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 220.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Have an account? ", fontSize = 16.sp)
                TextButton(onClick = {
                    navController?.navigate("login")
                }) {
                    Text("Login", color = Color(0xFF6C63FF), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }

        ButtonAuthComp(
            text = if (loading) "Registrando..." else "Get Started",
            onClick = {
                loading = true
                scope.launch {
                    try {
                        // creamos usuario en Firebase
                        val authResult = auth.createUserWithEmailAndPassword(email, password).await()
                        val uid = authResult.user?.uid
                        if (uid != null) {
                            val userId = getNextUserId()
                            val mapping = hashMapOf("userId" to userId)
                            // Guarda el mapping
                            Firebase.firestore.collection("user_mappings")
                                .document(uid)
                                .set(mapping)
                                .await()
                            // Inicializa el carrito default
                            Firebase.firestore.collection("carts")
                                .document(userId.toString())
                                .set(
                                    hashMapOf(
                                        "id" to userId,
                                        "products" to emptyList<Map<String, Any>>(), // lista vacía sin productos
                                        "total" to 0.0,
                                        "totalProducts" to 0,
                                        "totalQuantity" to 0
                                    )
                                )
                                .await()
                            Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()

                            // Navegamos al home y eliminamos la ruta /register
                            navController?.navigate("homeScreen") {
                                popUpTo("register") { inclusive = true }
                            }
                        }
                    } catch (e: Exception) {
                        loading = false
                        Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }
            },
            enabled = isButtonEnabled
        )

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterViewPreview() {
    TP3PETSHOPTheme(darkTheme = false, dynamicColor = false) {
        RegisterView()
    }
}