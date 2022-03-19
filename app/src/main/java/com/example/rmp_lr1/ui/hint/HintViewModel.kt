package com.example.rmp_lr1.ui.hint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rmp_lr1.HintViewModelAssistedFactory
import com.example.rmp_lr1.R
import com.example.rmp_lr1.data.repository.QuestionRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class HintViewModel @AssistedInject constructor(
    @Assisted private val questionId: Int,
    private val repository: QuestionRepository
) : ViewModel() {

    private val _isAnswerTrue: MutableLiveData<Int> = MutableLiveData()
    val isAnswerTrue: LiveData<Int> = _isAnswerTrue

    init{
        getQuestionAnswer()
    }

    private fun getQuestionAnswer() {
        val questions = repository.getQuestions()
        val isAnswerCorrect = questions[questionId].isAnswerTrue

        if(isAnswerCorrect) {
            _isAnswerTrue.value = R.string.correct_answer
        } else {
            _isAnswerTrue.value = R.string.incorrect_answer
        }
    }

    class Factory(
        private val assistedFactory: HintViewModelAssistedFactory,
        private val questionId: Int
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return assistedFactory.create(questionId) as T
        }
    }
}

