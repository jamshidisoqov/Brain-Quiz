package uz.gita.robo_brain.presentation.ui.quick_math.screens.result

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.robo_brain.R
import uz.gita.robo_brain.databinding.FragmentQuickMathResultBinding
import uz.gita.robo_brain.presentation.ui.quick_math.view_model.QuickMathResultViewModel
import uz.gita.robo_brain.presentation.ui.quick_math.view_model.impl.QuickMathResultViewModelImpl

class QuickMathResultFragment : Fragment(R.layout.fragment_quick_math_result) {

    private val viewModel: QuickMathResultViewModel by viewModels<QuickMathResultViewModelImpl>()

    private val viewBinding: FragmentQuickMathResultBinding by viewBinding()

    private val args: QuickMathResultFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.refreshLiveData.observe(this) {
            findNavController().navigate(QuickMathResultFragmentDirections.actionQuickMathResultFragmentToQuickMathFragment())
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