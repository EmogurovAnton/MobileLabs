package com.example.rmp_lr1.ui.questions

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rmp_lr1.QuestionsViewModelAssistedFactory
import com.example.rmp_lr1.R
import com.example.rmp_lr1.appComponent
import com.example.rmp_lr1.databinding.FragmentQuestionsBinding
import com.example.rmp_lr1.extensions.getNavigationResult
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class QuestionsFragment : Fragment(R.layout.fragment_questions) {
    private var currentIndex = 0
    private lateinit var binding: FragmentQuestionsBinding

    @Inject
    lateinit var assistedFactory: QuestionsViewModelAssistedFactory

    private val viewModel by viewModels<QuestionsViewModel> {
        QuestionsViewModel.Factory(
            assistedFactory = assistedFactory,
            currentIndex = currentIndex
        )
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentQuestionsBinding.bind(view)

        with(binding) {
            viewModel.question.observe(viewLifecycleOwner, { questionTextId ->
                textQuestion.text = getString(questionTextId)
            })

            buttonNextQuestion.setOnClickListener {
                viewModel.onNextClicked()
            }

            buttonPreviousQuestion.setOnClickListener {
                viewModel.onPreviousClicked()
            }

            buttonTrue.setOnClickListener {
                val userAnswer = true
                viewModel.checkAnswer(userAnswer)
            }

            buttonFalse.setOnClickListener {
                val userAnswer = false
                viewModel.checkAnswer(userAnswer)
            }

            viewModel.resourceId.observe(viewLifecycleOwner, { resourceId ->
                showSnackbar(resourceId)
            })

            viewModel.correctIndex.observe(viewLifecycleOwner, { questionId ->
                textShowHint.setOnClickListener {
                    val action = questionId?.let { questionId ->
                        QuestionsFragmentDirections
                            .actionQuestionsFragmentToHintFragment(questionId)
                    }
                    action?.let { fragmentId -> findNavController().navigate(fragmentId) }
                }
            })

            val isHintUsed = checkCheating()
            viewModel.checkHint(isHintUsed)

            viewModel.hintMessage.observe(viewLifecycleOwner, {
                showSnackbar(it)
            })
        }
    }

    private fun checkCheating(): Boolean {
        val isHintUsed = getNavigationResult<Boolean>("isHintUsed") ?: false

        return isHintUsed
    }

    private fun showSnackbar(resourceId: Int) {
        val message = getString(resourceId)
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}