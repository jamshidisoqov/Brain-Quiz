package uz.uni_team.robo_brain.presentation.ui.quick_math.screens

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.uni_team.robo_brain.R
import uz.uni_team.robo_brain.databinding.FragmentQuickMathBinding
import uz.uni_team.robo_brain.presentation.ui.quick_math.view_model.QuickMathViewModel
import uz.uni_team.robo_brain.presentation.ui.quick_math.view_model.impl.QuickMathViewModelImpl
import uz.uni_team.robo_brain.repository.models.MathData

class QuickMathFragment : Fragment(R.layout.fragment_quick_math) {

    private val viewModel: QuickMathViewModel by viewModels<QuickMathViewModelImpl>()

    private val viewBinding: FragmentQuickMathBinding by viewBinding()

    private lateinit var variants: List<TextView>

    private lateinit var player: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.finishLiveData.observe(this, finishGameObserver)
        viewModel.timer.observe(this, timerObserver)
        viewModel.questionCountLiveData.observe(this, questionCountObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.apply {
            variants = listOf(this.tvVariant1, this.tvVariant2, this.tvVariant3, this.tvVariant4)
        }

        player = MediaPlayer.create(requireContext(), R.raw.sound)

        for (i in variants.indices) {
            val variant = variants[i]
            variant.tag = i
            variant.setOnClickListener {
                viewModel.check(it.tag.toString().toInt())
            }
        }
        viewModel.questionLiveData.observe(viewLifecycleOwner, questionObserver)
        viewModel.musicLiveData.observe(viewLifecycleOwner,musicObserver)
    }


    private val questionObserver = Observer<MathData> {
        viewBinding.apply {
            tvQuestion.text = it.question
            for (i in variants.indices) {
                variants[i].text = it.variants[i].toString()
            }
        }
    }

    private val finishGameObserver = Observer<Int> {
        findNavController().navigate(QuickMathFragmentDirections.actionQuickMathFragmentToQuickMathResultFragment(it))
    }

    private val timerObserver = Observer<Int> {
        viewBinding.progressHorizontalTrueFalse.progress = it * 10f
        viewBinding.tvTimer.text = it.toString()
    }

    @SuppressLint("SetTextI18n")
    private val questionCountObserver = Observer<Int> {
        viewBinding.tvQuestionCount.text = "Question:$it"
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