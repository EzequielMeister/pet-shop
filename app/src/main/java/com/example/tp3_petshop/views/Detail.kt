package com.example.tp3_petshop.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.tp3_petshop.components.TopBarSection
import com.example.tp3_petshop.viewmodel.CartViewModel
import com.example.tp3_petshop.viewmodel.ProductViewModel

@Composable
fun DetailView(productId: Int, navController: NavController, viewModel: ProductViewModel = viewModel()) {
    val product by viewModel.selectedProduct.collectAsState()
    var quantity by remember { mutableStateOf(1) }
    val cartViewModel: CartViewModel = viewModel()

    LaunchedEffect(productId) {
        viewModel.fetchProductById(productId)
    }

    if (product != null) {
        Scaffold { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    TopBarSection(
                        title = "Product Detail",
                        showFavorite = true,
                        onBackClick = { navController.popBackStack() },
                        onFavoriteClick = { /* TODO: marcar favorito */ }
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Imagen
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(240.dp)
                                .clip(RoundedCornerShape(24.dp))
                                .background(Color(0xFFF8F8F8)),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(product!!.thumbnail),
                                contentDescription = product!!.title,
                                modifier = Modifier
                                    .height(180.dp)
                                    .padding(16.dp),
                                contentScale = ContentScale.Fit
                            )
                        }

                        // Título
                        Text(
                            text = product!!.title,
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                        )

                        // Descripción
                        Text(
                            text = product!!.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                            lineHeight = 20.sp
                        )

                        // Cantidad y precio
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                IconButton(
                                    onClick = { if (quantity > 1) quantity-- },
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFFF2F2F2))
                                ) {
                                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Restar")
                                }

                                Text(
                                    text = quantity.toString(),
                                    style = MaterialTheme.typography.titleMedium
                                )

                                IconButton(
                                    onClick = { quantity++ },
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFFF2F2F2))
                                ) {
                                    Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Sumar")
                                }
                            }

                            Text(
                                text = "$${"%.2f".format(product!!.price)}",
                                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                            )
                        }
                    }
                    var navigateToCart by remember { mutableStateOf(false) }

                    if (navigateToCart) {
                        LaunchedEffect(Unit) {
                            kotlinx.coroutines.delay(500)
                            navController.navigate("cart")
                            navigateToCart = false
                        }
                    }

                    Button(
                        onClick = {
                            cartViewModel.addProductToCart(product!!.id, quantity)
                            navigateToCart = true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF735BF2)),
                        shape = RoundedCornerShape(30.dp)
                    ) {
                        Text(text = "Add to Cart", fontSize = 18.sp)
                    }
                }
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}
