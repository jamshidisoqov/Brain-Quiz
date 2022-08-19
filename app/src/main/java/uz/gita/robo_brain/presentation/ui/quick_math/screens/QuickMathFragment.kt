package uz.gita.robo_brain.presentation.ui.quick_math.screens

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.gita.robo_brain.R
import uz.gita.robo_brain.presentation.ui.quick_math.view_model.QuickMathViewModel

class QuickMathFragment : Fragment() {

    companion object {
        fun newInstance() = QuickMathFragment()
    }

    private lateinit var viewModel: QuickMathViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quick_math, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(QuickMathViewModel::class.java)
        // TODO: Use the ViewModel
    }

}