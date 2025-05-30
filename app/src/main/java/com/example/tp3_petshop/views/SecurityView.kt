package com.example.tp3_petshop.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tp3_petshop.components.SettingsItem
import com.example.tp3_petshop.components.MenuComponent
import com.example.tp3_petshop.components.TopBarButtonBack

@Composable
fun SecurityView (
    onNavigate: (value: String) -> Unit,
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        TopBarButtonBack("Security", fun () { onNavigate("settingsView")})

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            MenuComponent(title = "Security", items = listOf(
                SettingsItem(Icons.Outlined.Settings, "Change Password", "changePasswordView"),
                SettingsItem(Icons.Outlined.MailOutline, "Change Email", "changeEmailView"),
            ), fun (value: String) { onNavigate(value) })

        }

    }

}