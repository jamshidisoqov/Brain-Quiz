package uz.uni_team.robo_brain.presentation.ui.input_math.screens.result

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.uni_team.robo_brain.R
import uz.uni_team.robo_brain.databinding.FragmentInputMathResultBinding
import uz.uni_team.robo_brain.presentation.ui.input_math.view_model.InputMathResultViewModel
import uz.uni_team.robo_brain.presentation.ui.input_math.view_model.impl.InputMathResultViewModelImpl

class InputMathResultFragment : Fragment(R.layout.fragment_input_math_result) {

    private val viewModel: InputMathResultViewModel by viewModels<InputMathResultViewModelImpl>()

    private val viewBinding: FragmentInputMathResultBinding by viewBinding()

    private val args: InputMathResultFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.refreshLiveData.observe(this) {
            findNavController().navigate(InputMathResultFragmentDirections.actionInputMathResultFragmentToInputMathFragment())
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