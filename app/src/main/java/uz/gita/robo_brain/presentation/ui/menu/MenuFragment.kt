package uz.gita.robo_brain.presentation.ui.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.robo_brain.R
import uz.gita.robo_brain.databinding.FragmentMenuBinding
import uz.gita.robo_brain.presentation.ui.menu.view_model.MenuViewModel
import uz.gita.robo_brain.presentation.ui.menu.view_model.impl.MenuViewModelImpl

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private val viewModel: MenuViewModel by viewModels<MenuViewModelImpl>()

    private val binding: FragmentMenuBinding by viewBinding()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openPuzzle15LiveData.observe(this, openPuzzle15Observer)
        viewModel.openQuickMathLiveData.observe(this, openQuickMathObserver)
        viewModel.openTrueFalseLiveData.observe(this, openTrueFalseObserver)
        viewModel.openInputMathLiveData.observe(this, openInputObserver)
        viewModel.openSortedMathLiveData.observe(this, openSortedMathObserver)
        viewModel.openTableOfGrowLiveData.observe(this, openTableOfGrowObserver)
        viewModel.openPuzzle2048LiveData.observe(this, openPuzzle2048Observer)
        viewModel.openHardMathLiveData.observe(this, openHardMathObserver)
        viewModel.openSupportLiveData.observe(this, openSupportObserver)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.apply {
            containerPuzzle15.setOnClickListener {
                viewModel.openPuzzle15()
            }
            containerQuickMath.setOnClickListener {
                viewModel.openQuickMath()
            }
            containerTrueFalse.setOnClickListener {
                viewModel.openTrueFalse()
            }
            containerInputMath.setOnClickListener {
                viewModel.openInputMath()
            }
            containerSortingMath.setOnClickListener {
                viewModel.openSortedMath()
            }
            containerTableOfGrow.setOnClickListener {
                viewModel.openTableOfGrow()
            }
            containerPuzzle2048.setOnClickListener {
                viewModel.openPuzzle2048()
            }
            containerHardMath.setOnClickListener {
                viewModel.openHardMath()
            }
            imageFlash.setOnClickListener {
                viewModel.openSupport()
            }
        }

    }


    private val openPuzzle15Observer = Observer<Unit> {
        findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToNumberPuzzleFragment())
    }
    private val openQuickMathObserver = Observer<Unit> {
        findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToQuickMathFragment())
    }
    private val openTrueFalseObserver = Observer<Unit> {
        findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToTrueFalseFragment())
    }
    private val openInputObserver = Observer<Unit> {

    }

    private val openTableOfGrowObserver = Observer<Unit> {
        findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToGrowTableFragment())
    }

    private val openSortedMathObserver = Observer<Unit> {
        findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToSortMathFragment())
    }
    private val openPuzzle2048Observer = Observer<Unit> {
        findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToPuzzle2048Fragment())
    }
    private val openHardMathObserver = Observer<Unit> {
        findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToQuickMathFragment())
    }
    private val openSupportObserver = Observer<Unit> {

    }

}