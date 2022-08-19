package uz.gita.robo_brain.presentation.ui.input_math.screens

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.gita.robo_brain.R

class InputMathFragment : Fragment() {

    companion object {
        fun newInstance() = InputMathFragment()
    }

    private lateinit var viewModel: InputMathViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_input_math, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InputMathViewModel::class.java)
        // TODO: Use the ViewModel
    }

}