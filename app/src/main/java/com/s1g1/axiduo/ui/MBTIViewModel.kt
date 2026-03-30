package com.s1g1.axiduo.ui

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateSetOf
import androidx.lifecycle.ViewModel
import com.s1g1.axiduo.domain.MBTICalculator
import com.s1g1.axiduo.model.Answer
import com.s1g1.axiduo.model.Question

class MBTIViewModel : ViewModel() {
    val questionMap: Map<Int, Question> = Question.entries.associateBy { it.id }
    /* ---------- ALL ANSWERS INITIALLY SET TO NEUTRAL ---------- */
    private var _answers = mutableStateMapOf<Int, Answer>().apply {
        questionMap.values.forEach { question ->
            put(question.id, Answer.Neutral)
        }
    }

    /* ---------- ALL QUESTION INITIALLY EXPANDED ---------- */
    private var _expandedQuestions = mutableStateSetOf<Int>().apply {
        addAll(questionMap.values.map { it.ordinal+1 })
    }

    val expandedCount: Int
        get() = _expandedQuestions.size
    val questionCount: Int
        get() = questionMap.values.size

    var percentage: Int = 0
        get() = (questionCount - expandedCount)*100/questionCount

    fun isExpanded(question: Question): Boolean = _expandedQuestions.contains(question.id)

    private fun resetPercentage(){
        percentage = 0
    }
    private fun resetAnswers(){
        _answers.clear()
        _answers = mutableStateMapOf<Int, Answer>().apply {
            questionMap.values.forEach { question ->
                put(question.id, Answer.Neutral)
            }
        }
    }
    private fun resetExpanded(){
        _expandedQuestions.clear()
        _expandedQuestions = mutableStateSetOf<Int>().apply {
            addAll(questionMap.values.map { it.ordinal+1 })}
    }
    fun reset(){
        resetAnswers()
        resetExpanded()
        resetPercentage()
    }
    fun toggleExpanded(questionId: Int) {
        if (_expandedQuestions.contains(questionId)) {
            _expandedQuestions.remove(questionId)
        } else {
            _expandedQuestions.add(questionId)
        }
    }

    fun getCurrentAnswer(questionId: Int) : Answer? {
        return _answers[questionId]
    }

    fun onAnswerSelected(questionId: Int, updatedAnswer: Answer) {
        _answers[questionId] = updatedAnswer
        _expandedQuestions.remove(questionId) // Hides options after selection
        percentage = (questionCount - expandedCount)*100/questionCount
    }

    fun getMBTIType() : String{
        val calculator : MBTICalculator = MBTICalculator()
        for ((qId, answer) in _answers){
            calculator.addQuestionAnswer(question = questionMap[qId], answer = answer)
        }
        return calculator.getTypeAsString()
    }

    fun getMBTITypes() : List<String>{
        val calculator : MBTICalculator = MBTICalculator()
        for ((qId, answer) in _answers){
            calculator.addQuestionAnswer(question = questionMap[qId], answer = answer)
        }
        return calculator.getPossibleTypes()
    }
}