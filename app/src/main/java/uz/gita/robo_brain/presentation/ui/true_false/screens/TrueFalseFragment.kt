package uz.gita.robo_brain.presentation.ui.true_false.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.robo_brain.R
import uz.gita.robo_brain.databinding.FragmentTrueFalseBinding
import uz.gita.robo_brain.presentation.ui.true_false.view_model.TrueFalseViewModel
import uz.gita.robo_brain.presentation.ui.true_false.view_model.impl.TrueFalseViewModelImpl
import uz.gita.robo_brain.repository.models.TrueFalseData

class TrueFalseFragment : Fragment(R.layout.fragment_true_false) {

    private val viewModel: TrueFalseViewModel by viewModels<TrueFalseViewModelImpl>()

    private val viewBinding: FragmentTrueFalseBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.questionCountLiveData.observe(this, questionCountObserver)
        viewModel.timer.observe(this, timerObserver)
        viewModel.finishLiveData.observe(this, finishGameObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.questionLiveData.observe(viewLifecycleOwner, questionObserver)
        viewBinding.tvNoBtn.setOnClickListener {
            viewModel.check(false)
        }
        viewBinding.tvYesBtn.setOnClickListener {
            viewModel.check(true)
        }
    }

    private val questionObserver = Observer<TrueFalseData> {
        viewBinding.tvQuestionTrueFalse.text = it.question
    }

    private val finishGameObserver = Observer<Int> {
        findNavController().navigate(
            TrueFalseFragmentDirections.actionTrueFalseFragmentToTrueFalseResultFragment(
                it
            )
        )
    }

    private val timerObserver = Observer<Int> {
        viewBinding.progressHorizontalTrueFalse.progress = it * 10f
        viewBinding.tvTimer.text = it.toString()
    }

    private val questionCountObserver = Observer<Int> {
        viewBinding.tvQuestionCount.text = "Question:$it"
    }

}