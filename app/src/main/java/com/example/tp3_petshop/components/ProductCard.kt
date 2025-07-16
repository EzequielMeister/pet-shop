package com.example.tp3_petshop.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.tp3_petshop.models.Product
import com.example.tp3_petshop.viewmodel.CartViewModel
import com.example.tp3_petshop.viewmodel.SessionViewModel

@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {
    val cartViewModel: CartViewModel = hiltViewModel()
    val sessionViewModel: SessionViewModel = hiltViewModel()
    val userId by sessionViewModel.userId.collectAsState()
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .width(156.dp)
            .height(210.dp)
            .padding(end = 12.dp),
        onClick = onClick,
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F8F8)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Imagen del producto
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(product.thumbnail),
                    contentDescription = product.title,
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Fit
                )
            }

            // Título y precio
            Column {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1
                )
                Text(
                    text = "$${"%.2f".format(product.price)}",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            // Botón "+"
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd
            ) {
                IconButton(
                    onClick = {  userId?.let { uid ->
                        cartViewModel.setUserId(uid)
                        cartViewModel.addProductToCart(product.id, 1)
                        Toast.makeText(
                            context,
                            "1 x ${product.title} added to cart",
                            Toast.LENGTH_SHORT
                        ).show()
                    } },
                    modifier = Modifier
                        .size(32.dp)
                        .background(color = Color(0xFF7140FD), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add to cart",
                        tint = Color.White
                    )
                }
            }
        }
    }
}
