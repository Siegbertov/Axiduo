package com.s1g1.axiduo.domain

import com.s1g1.axiduo.model.Answer
import com.s1g1.axiduo.model.MBTIAxis
import com.s1g1.axiduo.model.Question

class MBTICalculator {
    private var energyCount: Int = 0
    private var mindCount: Int = 0
    private var natureCount: Int = 0
    private var tacticCount: Int = 0

    fun addQuestionAnswer(question: Question?, answer: Answer) {
        when (question?.axis) {
            MBTIAxis.Energy -> energyCount += question.axisDirection * answer.answerWeight
            MBTIAxis.Mind -> mindCount += question.axisDirection * answer.answerWeight
            MBTIAxis.Nature -> natureCount += question.axisDirection * answer.answerWeight
            MBTIAxis.Tactics -> tacticCount += question.axisDirection * answer.answerWeight
            else -> {}
        }
    }

    fun getTypeAsString(): String {
        val resultString = buildString {
            append(if (energyCount >= 0) MBTIAxis.Energy.positive else MBTIAxis.Energy.negative)
            append(if (mindCount >= 0) MBTIAxis.Mind.positive else MBTIAxis.Mind.negative)
            append(if (natureCount >= 0) MBTIAxis.Nature.positive else MBTIAxis.Nature.negative)
            append(if (tacticCount >= 0) MBTIAxis.Tactics.positive else MBTIAxis.Tactics.negative)
        }
        return resultString
    }

    fun getPossibleTypes(): List<String>{
        fun getOptions(count: Int, axis: MBTIAxis): List<String> {
            return when {
                count > 0 -> listOf(axis.positive)
                count < 0 -> listOf(axis.negative)
                else -> listOf(axis.positive, axis.negative) //
            }
        }

        val energyOpts = getOptions(energyCount, MBTIAxis.Energy)
        val mindOpts = getOptions(mindCount, MBTIAxis.Mind)
        val natureOpts = getOptions(natureCount, MBTIAxis.Nature)
        val tacticOpts = getOptions(tacticCount, MBTIAxis.Tactics)

        return energyOpts.flatMap { e ->
            mindOpts.flatMap { m ->
                natureOpts.flatMap { n ->
                    tacticOpts.map { t -> "$e$m$n$t" }
                }
            }
        }
    }
}