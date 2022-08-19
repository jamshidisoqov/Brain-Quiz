package uz.gita.robo_brain.presentation.ui.true_false.screens

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.gita.robo_brain.R

class TrueFalseFragment : Fragment() {

    companion object {
        fun newInstance() = TrueFalseFragment()
    }

    private lateinit var viewModel: TrueFalseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_true_false, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TrueFalseViewModel::class.java)
        // TODO: Use the ViewModel
    }

}