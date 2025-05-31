package com.example.tp3_petshop.views

import androidx.compose.foundation.layout.*
import com.example.tp3_petshop.views.ProductListScreen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        containerColor = Color(0xFFFFFFFF),
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = { Text("Petshop") }
                // podés agregar navegación o íconos acá
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { /* Navegación */ },
                    label = { Text("Inicio") },
                    icon = { Icon(Icons.Default.Home, contentDescription = null) }
                )
                // Agregá más items si tenés otras pantallas
            }
        }
    ) { innerPadding ->
        // Contenido principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            ProductListScreen(navController = navController)
        }
    }
}
