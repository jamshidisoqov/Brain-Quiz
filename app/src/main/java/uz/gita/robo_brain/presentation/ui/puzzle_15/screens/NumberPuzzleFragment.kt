package uz.gita.robo_brain.presentation.ui.puzzle_15.screens

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.gita.robo_brain.R

class NumberPuzzleFragment : Fragment() {

    companion object {
        fun newInstance() = NumberPuzzleFragment()
    }

    private lateinit var viewModel: NumberPuzzleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_number_puzzle, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NumberPuzzleViewModel::class.java)
        // TODO: Use the ViewModel
    }

}