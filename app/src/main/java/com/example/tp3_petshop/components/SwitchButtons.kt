package com.example.tp3_petshop.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tp3_petshop.shared.ButtonOption

// pasar al theme
val backgroundColorButtonsContainer = Color(0xFFF8F8F8)
// color del boton activo
val activeButtonBackgroundColor = Color(0xFF7140FD)
// transparente
val inactiveButtonColor = Color.Transparent
//  color de texto inactivo
val inactiveTextColor = Color(0xFFB3B1B0)
// .align(Alignment.CenterHorizontally)
val activeTextColor = Color(0xFFFFFFFF)

@Composable
fun SwitchButtons(
    options: List<ButtonOption>,
    selectedValue: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.wrapContentHeight()
        .wrapContentSize()
        .clip(RoundedCornerShape(20.dp))
        .background(backgroundColorButtonsContainer),
    ) {
        Spacer(modifier = Modifier.width(4.dp))
        options.forEach { option ->
            Button(
                onClick = { onChange(option.value) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedValue == option.value) activeButtonBackgroundColor else inactiveButtonColor
                )
            ) {
                Text(
                    option.label,
                    color = if (selectedValue == option.value) activeTextColor else inactiveTextColor
                )
            }
        }
        Spacer(modifier = Modifier.width(4.dp))
    }
}
