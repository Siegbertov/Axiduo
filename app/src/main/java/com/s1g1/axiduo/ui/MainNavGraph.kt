package com.s1g1.axiduo.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.s1g1.axiduo.ui.graph.GridScreen
import com.s1g1.axiduo.ui.graph.StartScreen
import com.s1g1.axiduo.ui.graph.TestScreen
import com.s1g1.axiduo.ui.graph.TypeScreen

object Routes {
    val STARTSCREEN = "start_screen"
    val TESTSCREEN = "test_screen"
    val GRIDSCREEN = "grid_screen"
    val TYPESCREEN = "type_screen"
}


@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    mbtiViewModel: MBTIViewModel,
    isDarkTheme: Boolean,
    onToggleTheme: ()->Unit,
){
    val navController = rememberNavController()

    Scaffold(
        topBar= { AxiduoTopBar(
            isDarkTheme=isDarkTheme,
            onToggleTheme = onToggleTheme,
        ) }
    ){ innerPadding ->
        NavHost(navController=navController, startDestination= Routes.STARTSCREEN, builder={

            composable(route=Routes.STARTSCREEN){
                StartScreen(innerPadding = innerPadding, navController = navController)
            }

            composable(route=Routes.GRIDSCREEN){
                GridScreen(innerPadding = innerPadding, navController = navController)
            }

            composable(route=Routes.TESTSCREEN){
                TestScreen(
                    innerPadding = innerPadding,
                    navController = navController,
                    mbtiViewModel = mbtiViewModel
                )
            }

            composable(
                route=Routes.TYPESCREEN+"/{typeString}",
                arguments = listOf( navArgument(name="typeString"){type = NavType.StringType} )
            ){ backstackEntry ->
                backstackEntry.arguments?.let{
                    TypeScreen(
                        innerPadding = innerPadding,
                        typeString = it.getString("typeString")
                    )
                }
            }

        })
    }
}
