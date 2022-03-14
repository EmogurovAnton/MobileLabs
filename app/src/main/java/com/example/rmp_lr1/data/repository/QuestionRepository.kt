package com.example.rmp_lr1.data.repository

import com.example.rmp_lr1.R
import com.example.rmp_lr1.entities.Question
import javax.inject.Inject

class QuestionRepository @Inject constructor() {

    fun getQuestions(): ArrayList<Question> {
        val questions = arrayListOf<Question>()

        questions.add(
            Question(R.string.first_question, false)
        )
        questions.add(
            Question(R.string.second_question, false)
        )
        questions.add(
            Question(R.string.third_question, false)
        )
        questions.add(
            Question(R.string.fourth_question, true)
        )
        questions.add(
            Question(R.string.fifth_question, true)
        )
        questions.add(
            Question(R.string.fifth_question, true)
        )
        questions.add(
            Question(R.string.six_question, true)
        )
        questions.add(
            Question(R.string.seventh_question, true)
        )
        questions.add(
            Question(R.string.eighth_question, false)
        )
        questions.add(
            Question(R.string.eighth_question, false)
        )
        questions.add(
            Question(R.string.nine_question, false)
        )
        questions.add(
            Question(R.string.nine_question, false)
        )
        questions.add(
            Question(R.string.tenth_question, true)
        )

        return questions
    }
}