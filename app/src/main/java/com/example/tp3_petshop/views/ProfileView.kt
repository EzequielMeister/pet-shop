package com.example.tp3_petshop.views


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tp3_petshop.components.TwoOptionsButtons
import com.example.tp3_petshop.R
import com.example.tp3_petshop.components.ProfileButtons
import com.example.tp3_petshop.ui.theme.TP3PETSHOPTheme

@Composable
fun ProfileView() {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            TwoOptionsButtons(
                "Profile", "Seller Mode", "Seller Mode", {},
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            // Fondo + imagen superpuesta
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(209.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .height(159.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.profile_background),
                        contentDescription = "Fondo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                // Imagen circular superpuesta
                Image(
                    painter = painterResource(R.drawable.profile_photo_p),
                    contentDescription = "Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .offset(y = 109.dp) // baja desde el top, pero sobresale
                        .clip(CircleShape)
                )
            }
            Row {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    text = "Pittashop",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold

                )
            }
            Row(
                modifier= Modifier.width(224.dp)
                    .align(Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column {
                    Text(
                        text = "109",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Followers",
                        fontSize = 12.sp
                    )
                }
                Column {
                    Text(
                        text = "992",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Following",
                        fontSize = 12.sp
                    )

                }
                Column {
                    Text(
                        text = "80",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Sales",
                        fontSize = 12.sp
                    )

                }
            }
            ProfileButtons(
                "Product",
                "Product",
                "Sold",
                "Stats",
                handleChange = {},
                Modifier.align(Alignment.CenterHorizontally))

            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                text = "Fila 5",
                textAlign = TextAlign.Center,
            )
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



@Preview(showBackground = true)
@Composable

fun ProfileViewPreview () {
    TP3PETSHOPTheme (darkTheme = false, dynamicColor = false) {
        ProfileView()
    }
}