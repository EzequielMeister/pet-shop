package com.example.tp3_petshop.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tp3_petshop.shared.ButtonOption


val activeBackgroundButtonColor = Color(0xFF7140FD)
val inactiveBackgroundButtonColor = Color(0xFFF8F8F8)
val inactiveColor = Color(0xFFB3B1B0)

@Composable
fun TabsButton(
    modifier: Modifier = Modifier,
    options: List<ButtonOption>,
    selected: String,
    handleChange: (String) -> Unit = {}
) {
    Row(
        modifier = modifier.width(275.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        options.forEach { option ->
            Button(
                onClick = { handleChange(option.value) },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.height(47.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selected == option.value) activeBackgroundButtonColor else inactiveBackgroundButtonColor
                )
            ) {
                Text(
                    text = option.label,
                    color = if (selected != option.value) inactiveColor else Color.White
                )
            }
        }
    }
}
