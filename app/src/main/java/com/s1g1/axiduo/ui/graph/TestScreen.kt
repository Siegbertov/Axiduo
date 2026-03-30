package com.s1g1.axiduo.ui.graph

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.s1g1.axiduo.R
import com.s1g1.axiduo.ui.MBTIViewModel
import com.s1g1.axiduo.ui.Routes
import kotlinx.coroutines.launch

@Composable
fun TestScreen(
    innerPadding: PaddingValues,
    navController: NavHostController,
    mbtiViewModel: MBTIViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)){
        val listState = rememberLazyListState()

        MBTITestScreenResetButton(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            onResetButtonPressed = {mbtiViewModel.reset()},
            listState = listState
        )
    }
}

@Composable
fun MBTITestScreenResetButton(
    modifier: Modifier,
    listState: LazyListState,
    onResetButtonPressed: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    Column {
        FilledTonalButton(onClick = {
            onResetButtonPressed()
            coroutineScope.launch {
                listState.animateScrollToItem(0)
            }

        }, modifier = modifier) {
            Icon(
                imageVector = Icons.Default.Restore,
                contentDescription = stringResource(R.string.reset_button),
            )
            Text(text=stringResource(R.string.reset_button), fontSize = 15.sp)
        }
    }
}