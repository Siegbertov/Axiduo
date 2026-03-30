package com.s1g1.axiduo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.s1g1.axiduo.R
import com.s1g1.axiduo.ui.theme.AppLanguage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AxiduoTopBar(
    isDarkTheme: Boolean,
    onToggleTheme: ()->Unit,
    isLanguageExpanded: Boolean,
    onToggleLanguage: ()->Unit,
    onLanguageChange: (String)->Unit
){
    CenterAlignedTopAppBar(
        modifier = Modifier.statusBarsPadding(), // MUST HAVE
        title = { Text(stringResource(R.string.app_name)) },
        actions = {
            Row{
                DarkModeModeSwitcher(
                    isDarkTheme = isDarkTheme,
                    onToggleTheme = { onToggleTheme() })

                LanguageDDMenu(
                    isLanguageExpanded = isLanguageExpanded,
                    onToggleLanguage = {onToggleLanguage()},
                    onLanguageChange = {newLang -> onLanguageChange(newLang)}
                )
            }
        }
    )
}

@Composable
fun LanguageDDMenu(
    isLanguageExpanded: Boolean,
    onToggleLanguage: () -> Unit,
    onLanguageChange: (String) -> Unit
) {
    Box{
        IconButton(onClick={onToggleLanguage()}) {
            Box(contentAlignment = Alignment.Center){
                Icon(imageVector = Icons.Default.Language, contentDescription = null)
            }
        }
        DropdownMenu(expanded = isLanguageExpanded, onDismissRequest = { onToggleLanguage() }) {
            AppLanguage.entries.forEach { lang->
                DropdownMenuItem(
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(4.dp)
                        ){
                            Image(
                                modifier = Modifier
                                    .size(24.dp),
                                painter = painterResource(lang.flag),
                                contentScale = ContentScale.Crop,
                                contentDescription = lang.code
                            )
                            Spacer(modifier=Modifier.padding(4.dp))
                            Text(lang.fullName)
                        }

                    },
                    onClick = {
                        onLanguageChange(lang.code)
                        onToggleLanguage() }
                )
            }
        }
    }
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