package com.example.rmp_lr1.di

import com.example.rmp_lr1.ui.hint.HintFragment
import com.example.rmp_lr1.ui.questions.QuestionsFragment
import dagger.Component

@Component
interface AppComponent {
    fun inject(fragment: QuestionsFragment)
    fun inject(fragment: HintFragment)
}







