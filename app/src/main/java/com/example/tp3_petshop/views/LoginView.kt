package com.example.tp3_petshop.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tp3_petshop.R
import com.example.tp3_petshop.components.ButtonAuthComp
import com.example.tp3_petshop.components.FormAuth
import com.example.tp3_petshop.ui.theme.TP3PETSHOPTheme

@Composable
fun LoginView(navController: NavController? = null) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    val isButtonEnabled = email.isNotBlank() && password.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Column {
            Text("Hello, \nWelcome Back!", fontSize = 44.sp, lineHeight = 50.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Water is life. Water is a basic human need. In various lines of life, humans need water.",
                color = Color.Gray,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(24.dp))
            FormAuth(
                value = email,
                onValueChange = {  email = it},
                placeholder = "Email",
                isError = showError
            )
            Spacer(modifier = Modifier.height(20.dp))
            FormAuth(
                value = password,
                onValueChange = {password = it},
                placeholder = "Password",
                isError = false
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (showError && (email.isBlank() || password.isBlank())) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.info),
                        contentDescription = "Error",
                        tint = Color.Red,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Required Fields", color = Color.Red, fontSize = 12.sp)
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f))
                Text("  or  ", color = Color.Gray)
                HorizontalDivider(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                        .height(65.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.google_logo),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Google", fontSize = 16.sp)
                }

                OutlinedButton(
                    onClick = {},
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                        .height(65.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.facebook_logo),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Facebook", fontSize = 16.sp)
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 250.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Don’t have an account? ", fontSize = 16.sp)
                TextButton(onClick = {
                    navController?.navigate("register")
                }) {
                    Text("Create Account", color = Color(0xFF6C63FF), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }

        ButtonAuthComp(
            text = "Get Started",
            onClick =  {},
            enabled = isButtonEnabled
        )

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun LoginViewPreview() {
    TP3PETSHOPTheme(darkTheme = false, dynamicColor = false) {
        LoginView()
    }
}