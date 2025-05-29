package com.example.tp3_petshop.views
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.tp3_petshop.ui.theme.TP3PETSHOPTheme
import kotlinx.coroutines.delay

@Composable
fun SplashView(onContinue: () -> Unit) {
    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                text = "Home",
                textAlign = TextAlign.Center,
            )
        }
    }
    LaunchedEffect(Unit) {
        delay(1500) // Simula carga de splash
        onContinue()
    }
}


@Preview(showBackground = true)
@Composable

fun SplashViewPreview () {
    TP3PETSHOPTheme (darkTheme = false, dynamicColor = false) {
        SplashView(onContinue = {})
    }
}