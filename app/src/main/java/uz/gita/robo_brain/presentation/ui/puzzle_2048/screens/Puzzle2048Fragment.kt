package uz.gita.robo_brain.presentation.ui.puzzle_2048.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.robo_brain.R
import uz.gita.robo_brain.databinding.FragmentPuzzle2048Binding
import uz.gita.robo_brain.utils.MovementDetector

class Puzzle2048Fragment : Fragment(R.layout.fragment_puzzle2048) {

    private val binding: FragmentPuzzle2048Binding by viewBinding()

    private val itemList = ArrayList<TextView>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val detector = MovementDetector(requireContext())
        detector.setOnMovementListener {

        }
        binding.headerContainer.setOnTouchListener(detector)
    }


    fun initView() {
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


}