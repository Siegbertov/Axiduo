package com.s1g1.axiduo.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.s1g1.axiduo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AxiduoTopBar(
    isDarkTheme: Boolean,
    onToggleTheme: ()->Unit,
){
    CenterAlignedTopAppBar(
        modifier = Modifier.statusBarsPadding(), // MUST HAVE
        title = { Text(stringResource(R.string.app_name)) },
        actions = {
            Row{
                DarkModeModeSwitcher(
                    isDarkTheme = isDarkTheme,
                    onToggleTheme = { onToggleTheme() })
            }
        }
    )
}

@Composable
fun DarkModeModeSwitcher(
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit
) {
    Switch(
        checked = isDarkTheme,
        onCheckedChange = { onToggleTheme() },
        thumbContent = {
            if(!isDarkTheme){
                Icon(imageVector = Icons.Default.LightMode, contentDescription = null)
            }else{
                Icon(imageVector = Icons.Default.DarkMode, contentDescription = null)
            }
        }
    )
}