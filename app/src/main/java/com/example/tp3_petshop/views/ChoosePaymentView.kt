package com.example.tp3_petshop.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tp3_petshop.components.ButtonAuthComp
import com.example.tp3_petshop.components.PaymentOption
import com.example.tp3_petshop.ui.theme.TP3PETSHOPTheme

@Composable
fun ChoosePaymentView(navController: NavController? = null) {
    var selectedMethod by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Payment Method",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
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
            onClick = {navController?.navigate("paysuccess")},
            enabled = selectedMethod != null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChoosePaymentViewPreview() {
    TP3PETSHOPTheme(darkTheme = false, dynamicColor = false) {
        ChoosePaymentView()
    }
}