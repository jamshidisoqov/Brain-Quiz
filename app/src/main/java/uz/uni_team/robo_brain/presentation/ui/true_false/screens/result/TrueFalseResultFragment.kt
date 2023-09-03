package uz.uni_team.robo_brain.presentation.ui.true_false.screens.result

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.uni_team.robo_brain.R
import uz.uni_team.robo_brain.databinding.FragmentTrueFalseResultBinding
import uz.uni_team.robo_brain.presentation.ui.sort_math.screens.result.SortedMathResultFragmentArgs
import uz.uni_team.robo_brain.presentation.ui.true_false.screens.result.impl.TrueFalseResultViewModelImpl

class TrueFalseResultFragment : Fragment(R.layout.fragment_true_false_result) {

    private val viewModel: TrueFalseResultViewModel by viewModels<TrueFalseResultViewModelImpl>()

    private val viewBinding: FragmentTrueFalseResultBinding by viewBinding()

    private val args: SortedMathResultFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.refreshLiveData.observe(this) {
            findNavController().navigate(TrueFalseResultFragmentDirections.actionTrueFalseResultFragmentToTrueFalseFragment())
        }
        viewModel.backLiveData.observe(this) {
            findNavController().navigateUp()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.bestResultLiveData.observe(viewLifecycleOwner) {
            viewBinding.tvBest.text = it.toString()
        }
        viewBinding.imageBack.setOnClickListener {
            viewModel.backClick()
        }
        viewBinding.imageRefresh.setOnClickListener {
            viewModel.refreshClick()
        }
        viewBinding.tvCurrent.text = args.level.toString()
    }
}