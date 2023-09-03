package uz.uni_team.robo_brain.presentation.ui.input_math.screens

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.uni_team.robo_brain.R
import uz.uni_team.robo_brain.databinding.FragmentInputMathBinding
import uz.uni_team.robo_brain.presentation.ui.input_math.view_model.InputMathViewModel
import uz.uni_team.robo_brain.presentation.ui.input_math.view_model.impl.InputMathViewModelImpl
import uz.uni_team.robo_brain.repository.models.InputMathData
import uz.uni_team.robo_brain.utils.getTimeFormat
import uz.uni_team.robo_brain.utils.invisible
import uz.uni_team.robo_brain.utils.visible

class InputMathFragment : Fragment(R.layout.fragment_input_math) {

    private val viewModel: InputMathViewModel by viewModels<InputMathViewModelImpl>()

    private val binding: FragmentInputMathBinding by viewBinding()

    private lateinit var player: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.finishLiveData.observe(this, finishObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        player = MediaPlayer.create(requireContext(), R.raw.sound)
        initView()
        binding.btnCheck.setOnClickListener {
            val text = binding.tvAnswer.text.toString()
            if (text.isDigitsOnly()) {
                viewModel.check(text.toInt())
            }else if (text.contains("-")&&text.length>1){
                viewModel.check(text.toInt())
            }
        }
        viewModel.answerLiveData.observe(viewLifecycleOwner, answerObserver)
        viewModel.questionLiveData.observe(viewLifecycleOwner, questionObserver)
        viewModel.timerLiveData.observe(viewLifecycleOwner, timerObserver)
        viewModel.questionCountLiveData.observe(viewLifecycleOwner, questionCountObserver)
        viewModel.minusObserver.observe(viewLifecycleOwner, minusObserver)
        viewModel.musicLiveData.observe(viewLifecycleOwner,musicObserver)
    }

    private fun initView() {
        val container = binding.linearLayout
        val size = container.childCount
        for (i in 0 until size) {
            val group: ViewGroup = container.getChildAt(i) as ViewGroup
            for (j in 0 until group.childCount) {
                val view = group.getChildAt(j)
                view.setOnClickListener {
                    viewModel.clickAnswer(
                        if (view is TextView) {
                            if (view.text == "-") {
                                -2
                            } else
                                view.text.toString().toInt()
                        } else -1
                    )
                }
            }
        }
    }

    private val finishObserver = Observer<Int> {
        findNavController().navigate(
            InputMathFragmentDirections.actionInputMathFragmentToInputMathResultFragment(
                it
            )
        )
    }

    private val timerObserver = Observer<Int> {
        binding.progressHorizontalSortedMath.progress = it * 5f
        binding.tvTime.text = it.getTimeFormat()
    }

    @SuppressLint("SetTextI18n")
    private val questionCountObserver = Observer<Int> {
        binding.tvQuestion.text = "Question: $it"
    }
    private val questionObserver = Observer<InputMathData> {
        binding.tvQuestionInput.text = it.question
    }
    private val answerObserver = Observer<String> {
        binding.tvAnswer.text = it
    }
    private val minusObserver = Observer<Int> {
        if (it == 1) {
            binding.btnMinus.visible()
            binding.btnMinus.isEnabled = true
        } else {
            binding.btnMinus.isEnabled = false
            binding.btnMinus.invisible()
        }
    }

    private val musicObserver = Observer<Boolean> {
        if (it) {
            player.start()
            player.isLooping = true
        }
    }

    override fun onDestroyView() {
        if (player.isPlaying) {
            player.stop()
        }
        super.onDestroyView()
    }
}