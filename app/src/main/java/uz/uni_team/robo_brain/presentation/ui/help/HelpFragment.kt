package uz.uni_team.robo_brain.presentation.ui.help

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.uni_team.robo_brain.R
import uz.uni_team.robo_brain.databinding.FragmentHelpBinding
import uz.uni_team.robo_brain.presentation.ui.help.impl.HelpViewModelImpl

class HelpFragment : Fragment(R.layout.fragment_help) {

    private val viewModel: HelpViewModel by viewModels<HelpViewModelImpl>()

    private val viewBinding: FragmentHelpBinding by viewBinding()

    private val adapter: HelpAdapter by lazy {
        HelpAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.backLiveData.observe(this) {
            findNavController().navigateUp()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.imageBack.setOnClickListener {
            viewModel.back()
        }
        viewBinding.listHelp.adapter = adapter
        viewModel.helpLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

}