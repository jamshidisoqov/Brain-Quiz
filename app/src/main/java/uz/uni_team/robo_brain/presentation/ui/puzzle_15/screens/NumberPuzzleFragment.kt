package uz.uni_team.robo_brain.presentation.ui.puzzle_15.screens

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.uni_team.robo_brain.R
import uz.uni_team.robo_brain.data.models.StatisticsByPuzzle15
import uz.uni_team.robo_brain.databinding.FragmentNumberPuzzleBinding
import uz.uni_team.robo_brain.presentation.ui.puzzle_15.view_model.NumberPuzzleViewModel
import uz.uni_team.robo_brain.presentation.ui.puzzle_15.view_model.impl.NumberPuzzleViewModelImpl
import uz.uni_team.robo_brain.repository.models.Coordinate
import uz.uni_team.robo_brain.utils.getTime
import uz.uni_team.robo_brain.utils.invisible
import uz.uni_team.robo_brain.utils.visible
import uz.uni_team.robo_brain.utils.zoomAnim

class NumberPuzzleFragment : Fragment(R.layout.fragment_number_puzzle) {

    private val viewModel: NumberPuzzleViewModel by viewModels<NumberPuzzleViewModelImpl>()

    private val viewBinding: FragmentNumberPuzzleBinding by viewBinding()

    private var checked = false

    private lateinit var player: MediaPlayer

    private val buttonsList = ArrayList<TextView>(16)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.numbers.observe(this, numbersObserver)
        viewModel.modifiedCoordinate.observe(this, modifiedCoordinateObserver)
        viewModel.backLiveData.observe(this, backObserver)
        viewModel.openResultLiveData.observe(this, openResultObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        player = MediaPlayer.create(requireContext(), R.raw.sound)
        loadButtons()
        viewModel.time.observe(viewLifecycleOwner, timeObserver)
        viewModel.moves.observe(viewLifecycleOwner, movesObserver)
        viewModel.musicLiveData.observe(viewLifecycleOwner, musicObserver)
        viewBinding.imageRefresh.setOnClickListener {
            viewModel.newGame()
        }
        viewBinding.imageBack.setOnClickListener {
            viewModel.back()
        }
    }

    private fun loadButtons() {
        val container = viewBinding.headerContainer
        val size = container.childCount
        for (i in 0 until size) {
            val group: ViewGroup = container.getChildAt(i) as ViewGroup
            for (j in 0 until size) {
                val view = group.getChildAt(j) as TextView
                view.tag = Coordinate(j, i)
                view.setOnClickListener {
                    val coordinate = view.tag as Coordinate
                    viewModel.move(coordinate.x, coordinate.y)
                }
                buttonsList.add(view)

            }
        }
    }

    private val numbersObserver = Observer<List<Int>> {
        for (i in buttonsList.indices) {
            buttonsList[i].visible()
            if (it[i] == 0) {
                buttonsList[i].invisible()
            }
            buttonsList[i].text = it[i].toString()
        }
    }

    private val modifiedCoordinateObserver = Observer<Pair<Coordinate, Coordinate>> { it ->
        val player = MediaPlayer.create(requireContext(), R.raw.click)
        if (!player.isPlaying)
            player.start()
        val old = it.first.x + it.first.y * 4
        val new = it.second.x + it.second.y * 4
        buttonsList[old].apply {
            text = buttonsList[new].text
            this.zoomAnim()
            visible()
        }
        buttonsList[new].apply {
            text = "0"
            invisible()
        }
        if (new == 15) {
            viewModel.check(
                buttonsList.map { th ->
                    th.text.toString().toInt()
                },
                viewBinding.tvTimePuzzle15.getTime()
            )
        }

    }

    private val timeObserver = Observer<Int> {
        viewBinding.tvTimePuzzle15.apply {
            base = SystemClock.elapsedRealtime() - it
            start()
        }
    }

    private val movesObserver = Observer<Int> {
        viewBinding.tvMovedPuzzle15.apply {
            zoomAnim()
            text = it.toString()
        }
    }

    private val backObserver = Observer<Unit> {
        findNavController().navigateUp()
    }

    @SuppressLint("SetTextI18n")
    private val openResultObserver = Observer<StatisticsByPuzzle15> {
        checked = true
        findNavController().navigate(
            NumberPuzzleFragmentDirections.actionNumberPuzzleFragmentToPuzzle15ResultFragment(
                it
            )
        )
    }

    override fun onPause() {
        super.onPause()
        if (!checked) {
            viewModel.saveData(
                buttonsList.map {
                    it.text.toString().toInt()
                }, (SystemClock.elapsedRealtime() - viewBinding.tvTimePuzzle15.base).toInt()
            )
        }
    }

    private val musicObserver = Observer<Boolean> {
        if (it) {
            player.start()
            player.isLooping = true
        }
    }

    override fun onDestroyView() {
        if (player.isPlaying) {
            player.stop()
        }
        super.onDestroyView()
    }
}
