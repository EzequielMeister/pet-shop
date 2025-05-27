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
import com.example.tp3_petshop.views.DetailView
import com.example.tp3_petshop.views.HomeView
import com.example.tp3_petshop.views.SplashView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TP3PETSHOPTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "initial") {
                    composable("initial") {
                        SplashView(
                            // onLoginClick = { navController.navigate("login") },
                            //onRegisterClick = { navController.navigate("register") }

                        )
                    }
                    composable("home") {
                        HomeView()
                    }
                    composable("detail") {
                        DetailView()
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