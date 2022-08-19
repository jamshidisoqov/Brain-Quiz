package uz.gita.robo_brain.presentation.ui.hard_math.screens

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.gita.robo_brain.R
import uz.gita.robo_brain.presentation.ui.hard_math.view_model.HardMathViewModel

class HardMathFragment : Fragment() {

    companion object {
        fun newInstance() = HardMathFragment()
    }

    private lateinit var viewModel: HardMathViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hard_math, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HardMathViewModel::class.java)
        // TODO: Use the ViewModel
    }

}