package uz.gita.robo_brain.presentation.ui.sort_math.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.robo_brain.R
import uz.gita.robo_brain.databinding.FragmentSortMathBinding
import uz.gita.robo_brain.presentation.ui.sort_math.view_model.SortMathViewModel
import uz.gita.robo_brain.presentation.ui.sort_math.view_model.impl.SortMathViewModelImpl
import uz.gita.robo_brain.repository.models.SortedMath

class SortMathFragment : Fragment(R.layout.fragment_sort_math) {


    private val viewModel: SortMathViewModel by viewModels<SortMathViewModelImpl>()

    private val binding: FragmentSortMathBinding by viewBinding()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    private val questionObserver = Observer<SortedMath> {

    }

    private val checkObserver = Observer<Unit> {

    }

    private val gameOverObserver = Observer<Unit> {

    }

    private val questionCountObserver = Observer<Int> {

    }


}