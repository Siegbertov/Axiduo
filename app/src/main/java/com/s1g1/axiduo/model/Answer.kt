package com.s1g1.axiduo.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.s1g1.axiduo.R

enum class Answer(
    @StringRes val choice: Int,
    val color: Color,
    val answerWeight: Int
) {
    StronglyAgree(R.string.mbti_strongly_agree, Color(0xFF00FF00), 2),
    Agree(R.string.mbti_agree, Color(0xFFFFFF00), 1),
    Neutral(R.string.mbti_neutral, Color(0xFF808080), 0),
    Disagree(R.string.mbti_disagree, Color(0xFFFF8000), -1),
    StronglyDisagree(R.string.mbti_strongly_disagree, Color(0xFFFF0000), -2)
}