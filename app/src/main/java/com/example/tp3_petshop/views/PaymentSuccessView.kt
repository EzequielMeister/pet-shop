package com.example.tp3_petshop.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tp3_petshop.models.Order
import com.example.tp3_petshop.viewmodel.CartViewModel
import com.example.tp3_petshop.viewmodel.SessionViewModel

@Composable
fun PaymentSuccessView(navController: NavController? = null, paymentMethod: String, sessionViewModel: SessionViewModel = hiltViewModel(), cartViewModel: CartViewModel = hiltViewModel()) {
    // inyecta el viewmodel y vacia el carrito al montar el composable

    val userId by sessionViewModel.userId.collectAsState()
    val cart by cartViewModel.cart.collectAsState()

    LaunchedEffect(userId) {
        userId?.let {
            cartViewModel.setUserId(it)
            cartViewModel.getCart()
        }
    }

//    LaunchedEffect(Unit) { // unit = se lanza una sola vez al montar el composable
//        cartViewModel.clearCart()
//    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopStart),
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(80.dp)) // Espacio desde el top

            Text(
                text = "Payment",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Success!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Payment method: $paymentMethod",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "your order is being prepared by the shop, the courier will send it to your address", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(8.dp))

            cart?.products?.forEach { item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(item.title, modifier = Modifier.weight(1f))
                    Text(
                        "${item.quantity} x $${"%.2f".format(item.price)}",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        "$${"%.2f".format(item.quantity * item.price)}",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Text(
                text = "Total: $${"%.2f".format(cart?.total ?: 0.0)}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Button(
            onClick = {
                val uid = userId ?: "";
                cart?.let {
                    val order = it.products?.let { it1 ->
                        Order(
                            userId = uid.toString(),
                            items = it1,
                            total = it.total,
                            paymentMethod = paymentMethod
                        )
                    }
                    if (order != null) {
                        cartViewModel.createOrder(order) {
                            cartViewModel.clearCart {
                                navController?.navigate("homeScreen")
                            }
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF7A4DFF),
                contentColor = Color.White
            )
        ) {
            Text(text = "Go Home", fontWeight = FontWeight.Bold)
        }
    }
}