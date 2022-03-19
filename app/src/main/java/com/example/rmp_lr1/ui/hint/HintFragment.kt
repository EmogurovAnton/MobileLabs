package com.example.rmp_lr1.ui.hint

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.rmp_lr1.HintViewModelAssistedFactory
import com.example.rmp_lr1.QuestionsViewModelAssistedFactory
import com.example.rmp_lr1.R
import com.example.rmp_lr1.appComponent
import com.example.rmp_lr1.databinding.FragmentHintBinding
import com.example.rmp_lr1.extensions.setNavigationResult
import javax.inject.Inject

class HintFragment : Fragment(R.layout.fragment_hint) {
    private lateinit var binding: FragmentHintBinding
    private val args by navArgs<HintFragmentArgs>()

    @Inject
    lateinit var assistedFactory: HintViewModelAssistedFactory

    private val viewModel by viewModels<HintViewModel>() {
        HintViewModel.Factory(
            assistedFactory = assistedFactory,
            questionId = args.questionId
        )
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHintBinding.bind(view)

        with(binding) {
            viewModel.isAnswerTrue.observe(viewLifecycleOwner, {
                textAnswer.text = getString(it)
            })

            setNavigationResult(true, "isHintUsed")
        }
    }
}