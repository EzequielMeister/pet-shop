package com.example.tp3_petshop.views


import androidx.compose.foundation.Image
import androidx.compose.runtime.*
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tp3_petshop.R
import com.example.tp3_petshop.components.SwitchButtons
import com.example.tp3_petshop.components.TabsButton
import com.example.tp3_petshop.shared.ButtonOption
import com.example.tp3_petshop.ui.theme.TP3PETSHOPTheme


val options = listOf(
    ButtonOption("Product", "product"),
    ButtonOption("Sold", "sold"),
    ButtonOption("Stats", "stats")
)
val switchOptions = listOf(
    ButtonOption("Profile", "profile"),
    ButtonOption("Seller Mode", "seller"),
)

@Composable
fun ProfileViewSellerMode(
    navigate: () -> Unit
) {
    var selectedTab by remember { mutableStateOf("product") }
    var selectedSwitch by remember { mutableStateOf("seller") }

    val handleChangeTabs: (String) -> Unit = { value ->
        selectedTab = value
    }
    val handleChangeSwitchButton: (String) -> Unit = { value ->
        selectedSwitch = value
        navigate()
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            SwitchButtons(
                switchOptions,
                selectedSwitch,
                handleChangeSwitchButton,
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
            TabsButton(
                Modifier.align(Alignment.CenterHorizontally),
                options,
                selectedTab,
                handleChange = handleChangeTabs
            )
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

fun ProfileViewSellerModePreview () {
    TP3PETSHOPTheme (darkTheme = false, dynamicColor = false) {
        ProfileViewSellerMode({})
    }
}