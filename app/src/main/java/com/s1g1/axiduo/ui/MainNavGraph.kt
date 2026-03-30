package com.s1g1.axiduo.ui

import com.s1g1.axiduo.ui.theme.LanguageChangeHelper
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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

    val context = LocalContext.current
    val languageChangeHelper by lazy{ LanguageChangeHelper() }
    var isLanguageExpanded by remember { mutableStateOf(false) }
    var currentLanguageCode = languageChangeHelper.getLanguageCode(context)

    Scaffold(
        topBar= { AxiduoTopBar(
            isDarkTheme=isDarkTheme,
            onToggleTheme = onToggleTheme,
            isLanguageExpanded=isLanguageExpanded,
            onToggleLanguage = {isLanguageExpanded = !isLanguageExpanded},
            onLanguageChange = {newLang ->
                currentLanguageCode = newLang
                languageChangeHelper.changeLanguage(context,currentLanguageCode)}

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

@Composable
fun LanguageChangeHelper() {
    TODO("Not yet implemented")
}
