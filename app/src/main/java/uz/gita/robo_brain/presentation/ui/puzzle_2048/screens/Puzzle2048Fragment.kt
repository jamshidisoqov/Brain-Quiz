package uz.gita.robo_brain.presentation.ui.puzzle_2048.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.robo_brain.R
import uz.gita.robo_brain.databinding.FragmentPuzzle2048Binding
import uz.gita.robo_brain.presentation.ui.puzzle_2048.view_model.Puzzle2048ViewModel
import uz.gita.robo_brain.presentation.ui.puzzle_2048.view_model.impl.Puzzle2048ViewModelImpl
import uz.gita.robo_brain.utils.Constants
import uz.gita.robo_brain.utils.MovementDetector

class Puzzle2048Fragment : Fragment(R.layout.fragment_puzzle2048) {

    private val binding: FragmentPuzzle2048Binding by viewBinding()

    private val itemList = ArrayList<TextView>()

    private val viewModel: Puzzle2048ViewModel by viewModels<Puzzle2048ViewModelImpl>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.currentMatrix.observe(this, matrixObserver)
        viewModel.currentScore.observe(this, scoreObserver)
        viewModel.changePosition.observe(this, changePositionObserver)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initView()

        val detector = MovementDetector(requireContext())

        detector.setOnMovementListener {
            viewModel.move(it)
        }

        binding.headerContainer.setOnTouchListener(detector)

    }


    private fun initView() {
        val container = binding.headerContainer
        val size = container.childCount
        for (i in 0 until size) {
            val group: ViewGroup = container.getChildAt(i) as ViewGroup
            for (j in 0 until size) {
                val view = group.getChildAt(i) as TextView
                itemList.add(view)
            }
        }
    }


    private val matrixObserver = Observer<Array<Array<Int>>> {
        for (i in it.indices) {
            for (j in it.indices) {
                val view = itemList[i * it.size + j]
                val value = it[i][j]
                view.apply {
                    text = if (value == 0) ""
                    else "$value"
                    setBackgroundResource(Constants.getBackground(value))
                }
            }
        }
    }

    private val scoreObserver = Observer<Int> {
        binding.tvCurrent2048.text = it.toString()
    }
    private val changePositionObserver = Observer<Pair<Int, Int>> {
        viewModel.addScore(it.second)
    }


}