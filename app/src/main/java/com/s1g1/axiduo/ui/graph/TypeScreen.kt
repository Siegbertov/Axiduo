package com.s1g1.axiduo.ui.graph

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.s1g1.axiduo.model.MBTIType

@Composable
fun TypeScreen(
    innerPadding: PaddingValues,
    typeString: String?
) {
    val currentType = MBTIType.fromString(typeString?:"INTJ")
    currentType?.let{
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(20.dp),
            ){
                Image(
                    painterResource(currentType.img),
                    contentDescription = "TYPE IMAGE", /*TODO(CHANGE IT IN THE FUTURE)*/
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                )
                Text(text=currentType.typeName, fontSize = 40.sp, modifier=Modifier.padding(8.dp))
                Text(text= stringResource(currentType.nickname), fontSize = 30.sp, modifier=Modifier.padding(8.dp))
                Text(text=stringResource(currentType.description), fontSize = 20.sp, fontStyle = FontStyle.Italic)
            }
        }
    }
}