package com.example.rmp_lr1.ui.questions

import androidx.lifecycle.*
import com.example.rmp_lr1.QuestionsViewModelAssistedFactory
import com.example.rmp_lr1.R
import com.example.rmp_lr1.data.repository.QuestionRepository
import com.example.rmp_lr1.entities.Question
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class QuestionsViewModel @AssistedInject constructor(
    @Assisted private val currentIndex: Int,
    private val questionRepository: QuestionRepository
) : ViewModel() {

    private val arraySize = questionRepository.getQuestions().size

    private val _question: MutableLiveData<Int> = MutableLiveData()
    val question: LiveData<Int> = _question

    val resourceId: MutableLiveData<Int> = MutableLiveData()
    val correctIndex: MutableLiveData<Int?> = MutableLiveData()

    private val _hintMessage: MutableLiveData<Int> = MutableLiveData()
    val hintMessage: LiveData<Int> = _hintMessage


    init {
        getTextQuestion(currentIndex)
        correctIndex.value = currentIndex
    }

    fun onNextClicked() {
        var index = correctIndex.value
        index = (index?.plus(1))?.rem(arraySize)

        correctIndex.value = index

        index?.let { getTextQuestion(it) }
    }

    fun onPreviousClicked() {
        var index = correctIndex.value

        index = if (index == 0) {
            arraySize - 1
        } else {
            (index?.minus(1))?.rem(arraySize)
        }

        correctIndex.value = index

        index?.let { getTextQuestion(it) }
    }

    private fun getTextQuestion(index: Int) {
        val questions = questionRepository.getQuestions()
        val question = questions[index]

        _question.value = question.questionId
    }

    private fun getQuestion(index: Int): Question {
        val questions = questionRepository.getQuestions()

        return questions[index]
    }

    fun checkAnswer(userAnswer: Boolean) {
        val question = correctIndex.value?.let { getQuestion(it) }
        val isAnswerTrue = question?.isAnswerTrue

        val messageId = if (userAnswer == isAnswerTrue) {
            R.string.correct_answer
        } else {
            R.string.incorrect_answer
        }

        resourceId.value = messageId
    }

    fun checkHint(isHintUsed: Boolean) {
        if (isHintUsed) {
            _hintMessage.value = R.string.cheating_is_bad
        }
    }

    class Factory(
        private val assistedFactory: QuestionsViewModelAssistedFactory,
        private val currentIndex: Int
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return assistedFactory.create(currentIndex) as T
        }
    }
}
