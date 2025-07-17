package com.example.tp3_petshop.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tp3_petshop.R
import com.example.tp3_petshop.components.Banner
import com.example.tp3_petshop.components.BottomNavBar
import com.example.tp3_petshop.components.LocationTopBar
import com.example.tp3_petshop.components.TabsButton
import com.example.tp3_petshop.models.ButtonOption
import com.example.tp3_petshop.utils.Constants
import com.example.tp3_petshop.views.optionsHomeScreen

val optionsHomeScreen = listOf(
    ButtonOption("Food", "food"),
    ButtonOption("Toys", "toys"),
    ButtonOption("Accesories", "accesories")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(false) }
    val currentLocation = remember { mutableStateOf("Jebres, Surakarta") }
    var selectedTab by remember { mutableStateOf("food") }
    val handleChangeSwitchButton: (String) -> Unit = { value ->
        selectedTab = value
    }
    Scaffold(
        containerColor = Color(0xFFFFFFFF),
        topBar = {
            LocationTopBar(
                currentLocation = currentLocation.value,
                onLocationClick = { showBottomSheet = true },
                onNotificationClick = {
                    navController.navigate(Constants.Routes.NOTIFICATION_VIEW)
                },
                onSearchClick = {
                    navController.navigate(Constants.Routes.SEARCH_VIEW)
                }

            )
        },

        bottomBar = { BottomNavBar(currentRoute = "homeView", onNavigate = { route ->
            navController.navigate(route)
        }) }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Banner()
            TabsButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                optionsHomeScreen,
                selectedTab,
                handleChangeSwitchButton,
                allowFilter = true
            )
            Spacer(Modifier.height(16.dp))
            ProductListScreen(navController = navController)
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Location", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = "",
                        onValueChange = { },
                        placeholder = { Text("Search your Location") },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.LocationOn, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text("Track your Location", fontWeight = FontWeight.SemiBold)
                            Text(
                                "Automatically selects your current location",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}
