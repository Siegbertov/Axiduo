package com.s1g1.axiduo.ui

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.s1g1.axiduo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AxiduoTopBar(

){
    CenterAlignedTopAppBar(
        modifier = Modifier.statusBarsPadding(), // MUST HAVE
        title = { Text(stringResource(R.string.app_name)) },
    )
}