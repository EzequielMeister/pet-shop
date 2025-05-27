package com.example.tp3_petshop.views
import com.example.tp3_petshop.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview


import com.example.tp3_petshop.ui.theme.TP3PETSHOPTheme

@Composable
fun SplashView() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }

}


@Preview(showBackground = true)
@Composable

fun SplashViewPreview () {
    TP3PETSHOPTheme (darkTheme = false, dynamicColor = false) {
        SplashView()
    }
}