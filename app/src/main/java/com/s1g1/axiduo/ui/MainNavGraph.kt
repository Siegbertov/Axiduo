package com.s1g1.axiduo.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.s1g1.axiduo.ui.graph.StartScreen

object Routes {
    val STARTSCREEN = "start_screen"
    val TESTSCREEN = "test_screen"
    val GRIDSCREEN = "grid_screen"
    val TYPESCREEN = "type_screen"
}


@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
){
    val navController = rememberNavController()

    Scaffold(
        topBar= { AxiduoTopBar() }
    ){ innerPadding ->
        NavHost(navController=navController, startDestination= Routes.STARTSCREEN, builder={

            composable(route=Routes.STARTSCREEN){
                StartScreen(innerPadding = innerPadding, navController = navController)
            }
        })
    }
}