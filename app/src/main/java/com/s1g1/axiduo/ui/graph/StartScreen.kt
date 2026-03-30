package com.s1g1.axiduo.ui.graph

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.s1g1.axiduo.R
import com.s1g1.axiduo.ui.Routes

@Composable
fun StartScreen(
    innerPadding: PaddingValues,
    navController: NavHostController
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ){
        StartScreenGridButton( onTypeGridButtonPressed = {navController.navigate(Routes.GRIDSCREEN)} )

        StartScreenTestButton( onStartButtonPressed = {navController.navigate(Routes.TESTSCREEN)} )
    }
}

@Composable
fun StartScreenTestButton(onStartButtonPressed: () -> Unit) {
    Button(onClick = { onStartButtonPressed() }) {Text(text=stringResource(R.string.start_button), fontSize = 45.sp)}
}

@Composable
fun StartScreenGridButton (onTypeGridButtonPressed: () -> Unit) {
    Column {
        OutlinedButton(
            onClick = { onTypeGridButtonPressed() },
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.tertiary)
        ) {
            Text(text=stringResource(R.string.all_type_button), fontSize = 20.sp)}
    }
}