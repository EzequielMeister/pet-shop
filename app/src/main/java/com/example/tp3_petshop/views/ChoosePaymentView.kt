package com.example.tp3_petshop.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tp3_petshop.components.ButtonAuthComp
import com.example.tp3_petshop.components.PaymentOption
import com.example.tp3_petshop.ui.theme.TP3PETSHOPTheme
import com.example.tp3_petshop.viewmodel.CartViewModel
import com.example.tp3_petshop.viewmodel.SessionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChoosePaymentView(navController: NavController, sessionViewModel: SessionViewModel = hiltViewModel(), cartViewModel: CartViewModel = hiltViewModel()) {
    val userId by sessionViewModel.userId.collectAsState()
    var selectedMethod by remember { mutableStateOf<String?>(null) }
    val cart by cartViewModel.cart.collectAsState()

    LaunchedEffect(userId) {
        userId?.let {
            cartViewModel.setUserId(it)
            cartViewModel.getCart()
        }
    }

    Scaffold(
        topBar = {
        TopAppBar(
            title = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Payment Method", fontWeight = FontWeight.Bold)
                }
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            actions = {
                // Espacio invisible para balancear el ícono de la izquierda
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack, // mismo ancho
                        contentDescription = null,
                        tint = Color.Transparent // invisible
                    )
                }
            }

        )
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                cart?.let {
                    Text(
                        "${it.totalProducts} productos • Total: $${"%.2f".format(it.total)}",
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Choose your Payment Method",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                PaymentOption(
                    title = "Paypal",
                    selected = selectedMethod == "Paypal",
                    enabled = true,
                    onClick = { selectedMethod = if (selectedMethod == "Paypal") null else "Paypal" }
                )

                Spacer(modifier = Modifier.height(16.dp))

                PaymentOption(
                    title = "Bank Transfer",
                    selected = selectedMethod == "Bank Transfer",
                    enabled = true,
                    onClick = { selectedMethod = if (selectedMethod == "Bank Transfer") null else "Bank Transfer" }
                )
            }

            ButtonAuthComp(
                text = "Checkout",
                enabled = selectedMethod != null,
                onClick = {
                    cartViewModel.persistCart {
                        navController.navigate("paysuccess/$selectedMethod")
                    }
                }
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun ChoosePaymentViewPreview() {
    TP3PETSHOPTheme(darkTheme = false, dynamicColor = false) {
        //ChoosePaymentView()
    }
}