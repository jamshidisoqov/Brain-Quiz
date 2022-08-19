package uz.gita.robo_brain.presentation.ui.grow_table.screens

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.gita.robo_brain.R

class GrowTableFragment : Fragment() {

    companion object {
        fun newInstance() = GrowTableFragment()
    }

    private lateinit var viewModel: GrowTableViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_grow_table, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GrowTableViewModel::class.java)
        // TODO: Use the ViewModel
    }

}