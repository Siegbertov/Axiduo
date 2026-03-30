package com.s1g1.axiduo.ui.graph

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.s1g1.axiduo.model.MBTIType

@Composable
fun GridScreen(
    innerPadding: PaddingValues,
    navController: NavHostController
) {
    Column(modifier = Modifier.padding(innerPadding)){
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxHeight()

        ) {
            items(MBTIType.entries){ currentType ->
                ElevatedButton(
                    onClick = { /* TODO implement go to type screen */},
                    modifier = Modifier
                        .padding(10.dp)
                        .aspectRatio(1f)

                ){
                    Column(horizontalAlignment = Alignment.CenterHorizontally){
                        Text(text=currentType.typeName,fontSize = 20.sp)
                        Text(text= stringResource(currentType.nickname),fontSize = 10.sp)
                    }
                }
            }
        }
    }
}