package com.example.tp3_petshop.views


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tp3_petshop.components.TwoOptionsButtons

import com.example.tp3_petshop.R
import com.example.tp3_petshop.ui.theme.TP3PETSHOPTheme

// pasar al theme
val backgroundColorButtonsContainer = Color(0xFFF8F8F8)
// color del boton activo
val activeButtonBackgroundColor = Color(0xFF7140FD)
// transparente
val inactiveButtonColor = Color.Transparent
//  color de texto inactivo
val inactiveTextColor = Color(0xFFB3B1B0)


@Composable
fun ProfileView() {
    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(24.dp)
                .background(Color.Red),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            TwoOptionsButtons("Profile", "Seller Mode", "Seller Mode", {}, modifier = Modifier.align(Alignment.CenterHorizontally) )
            Spacer(modifier = Modifier.height(0.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(0.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.Cyan)
                    .height(159.dp)

            ) {
                Image(
                    painter = painterResource(R.drawable.profile_background),
                    contentDescription = "Icono SVG",
                    modifier = Modifier.fillMaxWidth().fillMaxHeight()
                        .fillMaxHeight()
                        .weight(1f),
                        contentScale = ContentScale.Crop
                )
            }
            Row {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    text = "Fila 3",
                    textAlign = TextAlign.Center,
                )

            }
            Row {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    text = "Fila 4",
                    textAlign = TextAlign.Center,
                )

            }
            Row {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    text = "Fila 5",
                    textAlign = TextAlign.Center,
                )

            }
            Row {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    text = "Fila 6",
                    textAlign = TextAlign.Center,
                )

            }
        }
    }
}


@Preview(showBackground = true)
@Composable

fun ProfileViewPreview () {
    TP3PETSHOPTheme (darkTheme = false, dynamicColor = false) {
        ProfileView()
    }
}