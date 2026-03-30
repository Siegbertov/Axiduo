package com.s1g1.axiduo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.s1g1.axiduo.ui.MBTIViewModel
import com.s1g1.axiduo.ui.MainNavGraph
import com.s1g1.axiduo.ui.theme.AxiduoTheme

class MainActivity : ComponentActivity() {

    private val mbtiViewModel: MBTIViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isDarkTheme by rememberSaveable { mutableStateOf(true) }
            AxiduoTheme(darkTheme = isDarkTheme) {
                MainNavGraph(
                    mbtiViewModel = mbtiViewModel,
                    isDarkTheme=isDarkTheme,
                    onToggleTheme = {isDarkTheme = !isDarkTheme},
                )
            }
        }
    }
}
