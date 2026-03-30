package com.s1g1.axiduo.ui.graph

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.s1g1.axiduo.R
import com.s1g1.axiduo.model.Answer
import com.s1g1.axiduo.model.Question
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
            listState = listState,
            onResetButtonPressed = {mbtiViewModel.reset()}
        )

        MBTITestList(
            modifier = Modifier.weight(1f),
            listState = listState,
            mbtiViewModel = mbtiViewModel
        )

        MBTITestScreenResultButton(
            mbtiViewModel = mbtiViewModel,
            onResultButtonPressed = { typeString ->
                navController.navigate(Routes.TYPESCREEN+"/${typeString}")
            }
        )

    }
}

@Composable
fun MBTITestScreenResultButton(
    mbtiViewModel: MBTIViewModel,
    onResultButtonPressed: (String) -> Unit
) {
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var possibleTypes by rememberSaveable { mutableStateOf(listOf<String>()) }

    Column {
        Button(
            onClick = {
                val currentTypes = mbtiViewModel.getMBTITypes()
                possibleTypes = currentTypes
                if (possibleTypes.size == 1){onResultButtonPressed(possibleTypes[0])} else {showDialog=true}
            },
            shape = RoundedCornerShape(bottomStart = 16.dp,bottomEnd = 16.dp),
            modifier = Modifier.fillMaxWidth(),
            enabled = mbtiViewModel.percentage == 100,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Green.copy(green=0.5f))
        ) {
            Text(
                text=if (mbtiViewModel.percentage == 100) stringResource(R.string.result_button) else "${mbtiViewModel.percentage}%",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(stringResource(R.string.possible_types)) },
                text = {
                    LazyColumn {
                        items(possibleTypes) { type ->
                            TextButton(onClick = {
                                showDialog = false
                                onResultButtonPressed(type)
                            }) {
                                Text(text = type)
                            }
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) { Text(stringResource(R.string.close_button)) }
                }
            )
        }
    }
}

@Composable
fun MBTITestList(
    modifier: Modifier,
    listState: LazyListState,
    mbtiViewModel: MBTIViewModel
) {
    LazyColumn(state = listState, modifier=modifier) {
        itemsIndexed(mbtiViewModel.questionMap.values.toList()) { qID, question ->
            val isExpanded = mbtiViewModel.isExpanded(question)

            QuestionItem(
                question = question,
                answer = mbtiViewModel.getCurrentAnswer(questionId = qID+1),
                isExpanded = isExpanded,
                onToggle = { mbtiViewModel.toggleExpanded(questionId = qID+1) },
                onAnswerSelected = {newAnswer -> mbtiViewModel.onAnswerSelected(questionId = qID+1, updatedAnswer = newAnswer)}
            )
            HorizontalDivider()
        }
    }
}

@Composable
fun QuestionItem(
    question: Question,
    answer: Answer?,
    isExpanded: Boolean,
    onToggle: () -> Unit,
    onAnswerSelected: (Answer) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)){
        Text(
            text =  "${question.id}. ${stringResource(question.sentence)}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { onToggle() },
            style = MaterialTheme.typography.titleMedium
        )
        answer?.let{
            if(!isExpanded){
                Text(
                    text = stringResource(answer.choice),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    fontStyle = FontStyle.Italic,
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.Black.copy(alpha = 0.9f),
                            offset = Offset(2f, 2f),
                            blurRadius = 4f
                        )
                    ),
                    color = answer.color
                )
            }
        }

        AnimatedVisibility(visible = isExpanded) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectableGroup(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                itemsIndexed(Answer.entries){ _, currentPressedRB ->
                    RadioButton(
                        selected = (answer == currentPressedRB),
                        onClick = {
                            println("New answer is - $currentPressedRB")
                            onAnswerSelected(currentPressedRB)
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = currentPressedRB.color,
                            unselectedColor = Color.Gray
                        )

                    )
                }
            }
        }
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