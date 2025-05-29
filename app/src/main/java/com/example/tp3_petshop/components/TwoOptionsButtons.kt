package com.example.tp3_petshop.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tp3_petshop.ui.theme.TP3PETSHOPTheme


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
fun TwoOptionsButtons(
    option1: String,
    option2: String,
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

        Button(
            onClick = { onChange(option1) },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedValue == option1) activeButtonBackgroundColor else inactiveButtonColor
            )
        ) {
            Text(
                option1,
                color = if (selectedValue == option1) activeTextColor else inactiveTextColor
            )
        }

        Button(
            onClick = { onChange(option2) },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedValue == option2) activeButtonBackgroundColor else inactiveButtonColor
            )
        ) {
            Text(
                option2,
                color = if (selectedValue == option2) activeTextColor else inactiveTextColor
            )
        }

        Spacer(modifier = Modifier.width(4.dp))
    }
}



@Preview(showBackground = true)
@Composable

fun TwoOptionsButtonsPreview () {
    TP3PETSHOPTheme (darkTheme = false, dynamicColor = false) {
        TwoOptionsButtons("Profile", "Seller Mode", "Profile", {})
    }
}
