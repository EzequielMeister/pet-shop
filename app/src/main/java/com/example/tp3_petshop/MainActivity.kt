package com.example.tp3_petshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tp3_petshop.ui.theme.TP3PETSHOPTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tp3_petshop.views.AccountView
import com.example.tp3_petshop.views.FaqView
import com.example.tp3_petshop.views.NotificationView
import com.example.tp3_petshop.views.PrivacyView
import com.example.tp3_petshop.views.ProfileView
import com.example.tp3_petshop.views.ProfileViewSellerMode
import com.example.tp3_petshop.views.SecurityView
import com.example.tp3_petshop.views.SettingsView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TP3PETSHOPTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "profileViewSellerMode") {
                    composable("profileViewSellerMode") {
                        ProfileViewSellerMode(
                            fun (){
                                navController.navigate("profileView")
                            },
                        )
                    }
                    composable("profileView") {
                        ProfileView(
                            fun() {
                                navController.navigate("profileViewSellerMode")
                            },
                            fun() {
                                navController.navigate("settingsView")
                            }
                        )
                    }
                    composable("settingsView") {
                        SettingsView(
                            fun(value: String) {
                                navController.navigate(value)
                            },
                            fun() {}
                        )
                    }
                    composable("privacyView") {
                        PrivacyView (
                            fun(value: String) {
                                navController.navigate(value)
                            }
                        )
                    }
                    composable("securityView") {
                        SecurityView (
                            fun(value: String) {
                                navController.navigate(value)
                            }
                        )
                    }
                    composable("faqView") {
                        FaqView (
                            fun(value: String) {
                                navController.navigate(value)
                            }
                        )
                    }
                    composable("notificationView") {
                        NotificationView (
                            fun(value: String) {
                                navController.navigate(value)
                            }
                        )
                    }
                    composable("accountView") {
                        AccountView(
                            fun(value: String) {
                                navController.navigate(value)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TP3PETSHOPTheme {
        Greeting("Android")
    }
}