package uz.gita.robo_brain.presentation.ui.puzzle_2048.screens

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.gita.robo_brain.R

class Puzzle2048Fragment : Fragment() {

    companion object {
        fun newInstance() = Puzzle2048Fragment()
    }

    private lateinit var viewModel: Puzzle2048ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_puzzle2048, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(Puzzle2048ViewModel::class.java)
        // TODO: Use the ViewModel
    }

}