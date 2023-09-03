package uz.uni_team.robo_brain.presentation.ui.puzzle_15.screens.result

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.uni_team.robo_brain.R
import uz.uni_team.robo_brain.data.models.StatisticsByPuzzle15
import uz.uni_team.robo_brain.databinding.FragmentPuzzle15ResultBinding
import uz.uni_team.robo_brain.presentation.ui.puzzle_15.screens.result.impl.Puzzle15ResultViewModelImpl
import uz.uni_team.robo_brain.utils.getTimeFormat

class Puzzle15ResultFragment : Fragment(R.layout.fragment_puzzle15_result) {

    private val viewBinding: FragmentPuzzle15ResultBinding by viewBinding()

    private val viewModel: Puzzle15ResultViewModel by viewModels<Puzzle15ResultViewModelImpl>()

    private val args: Puzzle15ResultFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.bestResult.observe(this, bestGameObserver)
        viewModel.backLiveData.observe(this, backLiveData)
        viewModel.refreshLiveData.observe(this, refreshObserver)
        viewModel.statisticsLiveData.observe(this, statisticsObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.apply {
            tvTimePuzzle15.text = args.statistics.time.getTimeFormat()
            tvMovedPuzzle15.text = args.statistics.moved.toString()
        }

        viewBinding.imageRefresh.setOnClickListener {
            viewModel.refreshClicked()
        }

    }

    private val refreshObserver = Observer<Unit> {
        findNavController().navigate(Puzzle15ResultFragmentDirections.actionPuzzle15ResultFragmentToNumberPuzzleFragment())
    }

    private val bestGameObserver = Observer<StatisticsByPuzzle15> {
        viewBinding.apply {
            tvMovedPuzzle15Best.text = it.moved.toString()
            tvTimePuzzle15Best.text = it.time.getTimeFormat()
        }
    }

    private val statisticsObserver = Observer<Unit> {

    }

    private val backLiveData = Observer<Unit> {

    }

}