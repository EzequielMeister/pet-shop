package com.example.tp3_petshop.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tp3_petshop.components.CartItemList
import com.example.tp3_petshop.viewmodel.CartViewModel
import com.example.tp3_petshop.viewmodel.SessionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartView(
    navController: NavController,
    sessionViewModel: SessionViewModel

) {
    val userId by sessionViewModel.userId.collectAsState()
    val cartViewModel: CartViewModel = hiltViewModel()
    val cart by cartViewModel.cart.collectAsState()
    val isCartEmpty = cart?.products.isNullOrEmpty()

    fun onCheckout() {
        cartViewModel.persistCart {
            navController.navigate("payment")
        }
    }

    // Cuando carga el Composable, si el ID es valido, seteamos y pedimos el cart
    LaunchedEffect(userId) {
        println("CartView: userId = $userId")
        if (userId != null && userId!! > 0) {
            cartViewModel.setUserId(userId)
            cartViewModel.getCart()
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Cart", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            if (!isCartEmpty) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                        )
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("${cart?.products?.sumOf { it.quantity } ?: 0} Items", color = Color.Gray)
                        Text("$${"%.2f".format(cart?.products?.sumOf { it.price * it.quantity } ?: 0.0)}", color = Color.Gray)
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Tax", color = Color.Gray)
                        Text("$1.99", color = Color.Gray)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Totals", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text("$${"%.2f".format(cart?.products?.sumOf { it.price * it.quantity }?.plus(1.99) ?: 0.0)}", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { onCheckout() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF735BF2)),
                        shape = RoundedCornerShape(30.dp)
                    ) {
                        Text("Checkout", fontSize = 18.sp)
                    }
                }
            }
        }
    ) { innerPadding ->
        when {
            // Loader
            cart == null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            isCartEmpty -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Your cart is empty!",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF735BF2)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { navController.navigate("homeScreen") },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF735BF2)),
                            shape = RoundedCornerShape(24.dp),
                            modifier = Modifier
                                .height(48.dp)
                                .width(200.dp)
                        ) {
                            Text(
                                text = "Continue Shopping",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White
                            )
                        }
                    }
                }
            }

            // Si no esta vacio mostramos la lista y le mandamos el onDelete click al ViewModel
            else -> {
                CartItemList(
                    products = cart?.products ?: emptyList(),
                    onDeleteClick = { productId -> cartViewModel.removeProduct(productId) },
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }

}

