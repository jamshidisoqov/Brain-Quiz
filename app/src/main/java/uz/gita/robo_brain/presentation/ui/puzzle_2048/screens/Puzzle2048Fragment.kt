package uz.gita.robo_brain.presentation.ui.puzzle_2048.screens

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import uz.gita.robo_brain.R
import uz.gita.robo_brain.databinding.FragmentPuzzle2048Binding
import uz.gita.robo_brain.presentation.ui.puzzle_2048.screens.dialog.GameOverDialog
import uz.gita.robo_brain.presentation.ui.puzzle_2048.screens.dialog.WinGameDialog
import uz.gita.robo_brain.presentation.ui.puzzle_2048.view_model.Puzzle2048ViewModel
import uz.gita.robo_brain.presentation.ui.puzzle_2048.view_model.impl.Puzzle2048ViewModelImpl
import uz.gita.robo_brain.utils.Constants
import uz.gita.robo_brain.utils.MovementDetector

class Puzzle2048Fragment : Fragment(R.layout.fragment_puzzle2048) {

    private val binding: FragmentPuzzle2048Binding by viewBinding()

    private val itemList = ArrayList<TextView>()

    private lateinit var player: MediaPlayer

    private val viewModel: Puzzle2048ViewModel by viewModels<Puzzle2048ViewModelImpl>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.currentScore.observe(this, scoreObserver)
        viewModel.changePosition.observe(this, changePositionObserver)
        viewModel.bestScore.observe(this, bestScoreObserver)
        viewModel.gameOver.observe(this, gameOverObserver)
        viewModel.winnerLiveData.observe(this,winnerObserver)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initView()

        player = MediaPlayer.create(requireContext(), R.raw.sound)

        viewModel.musicLiveData.observe(viewLifecycleOwner,musicObserver)

        binding.refreshContainer.setOnClickListener {
            viewModel.refresh()
            startAnimZoomIn(it)
        }
        binding.imageBack.setOnClickListener {
            startAnimZoomIn(it)
        }
        val detector = MovementDetector(requireContext())

        detector.setOnMovementListener {
            viewModel.move(it)
        }

        binding.headerContainer.setOnTouchListener(detector)

        viewModel.currentMatrix.observe(viewLifecycleOwner, matrixObserver)

    }


    private fun initView() {
        val container = binding.headerContainer
        val size = container.childCount
        for (i in 0 until size) {
            val group: ViewGroup = container.getChildAt(i) as ViewGroup
            for (j in 0 until size) {
                val view = group.getChildAt(j) as TextView
                itemList.add(view)
            }
        }
    }


    private val matrixObserver = Observer<Array<Array<Int>>> {
        for (i in it.indices) {
            for (j in it[i].indices) {
                val view = itemList[i * it.size + j]
                val value = it[i][j]
                view.apply {
                    text = if (value == 0) ""
                    else "$value"
                    setBackgroundResource(Constants.getBackground(value))
                }
            }
        }
    }

    private val scoreObserver = Observer<Int> {
        binding.tvCurrent2048.text = it.toString()
    }

    private val changePositionObserver = Observer<Pair<Int, Int>> {
        val player = MediaPlayer.create(requireContext(), R.raw.click)
        player.start()
        startAnimZoomIn(itemList[it.first * 4 + it.second])
        viewModel.addScore(it)
    }

    private fun startAnimZoomIn(view: View) {
        YoYo.with(Techniques.ZoomIn)
            .delay(0)
            .duration(100)
            .playOn(view)
    }

    private val bestScoreObserver = Observer<Int> {
        binding.tvBestPuzzle2048.text = it.toString()
    }

    override fun onDestroyView() {
        viewModel.quitGame()
        if (player.isPlaying) {
            player.stop()
        }
        super.onDestroyView()
    }

    private val gameOverObserver = Observer<Unit> {
        val dialog = GameOverDialog(requireContext())
        dialog.setFinishListener {
            dialog.dismiss()
            viewModel.refresh()
            findNavController().navigateUp()
        }
        dialog.setRetryListener {
            dialog.dismiss()
            viewModel.refresh()
        }
        dialog.show()
    }

    private val winnerObserver = Observer<Unit> {
        val dialog = WinGameDialog(requireContext())
        dialog.setDismissListener {
            dialog.dismiss()
            viewModel.refresh()
            findNavController().navigateUp()

        }
        dialog.setRetryClickListener {
            dialog.dismiss()
            viewModel.refresh()
        }
        dialog.show()
    }

    private val musicObserver = Observer<Boolean> {
        if (it) {
            player.start()
            player.isLooping = true
        }
    }



}