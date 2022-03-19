package com.example.rmp_lr1

import com.example.rmp_lr1.ui.hint.HintViewModel
import dagger.assisted.AssistedFactory

@AssistedFactory
interface HintViewModelAssistedFactory {
    fun create(questionId: Int) : HintViewModel
}