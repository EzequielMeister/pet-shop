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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


val activeBackgroundButtonColor = Color(0xFF7140FD)
val inactiveBackgroundButtonColor = Color(0xFFF8F8F8)
val inactiveColor = Color(0xFFB3B1B0)

@Composable
fun ProfileButtons(
    selected: String,
    firstOption: String,
    secondOption: String,
    thirdOption: String,
    handleChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.width(275.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = {},
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.height(47.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selected == firstOption) activeBackgroundButtonColor else inactiveBackgroundButtonColor
            )) {
            Text(text= "Product", color = if (selected !== firstOption) inactiveColor else Color.White)
        }
        Button(onClick = {},
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.height(47.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selected == secondOption) activeBackgroundButtonColor else inactiveBackgroundButtonColor
            )) {
            Text(text= "Sold", color = if (selected !== secondOption) inactiveColor else Color.White)
        }
        Button(onClick = {},
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.height(47.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selected == thirdOption) activeBackgroundButtonColor else inactiveBackgroundButtonColor
            )) {
            Text(text= "Stats",color = if (selected !== thirdOption) inactiveColor else Color.White)
        }

    }

}