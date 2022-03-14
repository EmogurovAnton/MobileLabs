package com.example.rmp_lr1

import com.example.rmp_lr1.ui.questions.QuestionsViewModel
import dagger.assisted.AssistedFactory

@AssistedFactory
interface QuestionsViewModelAssistedFactory {
    fun create(currentIndex: Int) : QuestionsViewModel
}