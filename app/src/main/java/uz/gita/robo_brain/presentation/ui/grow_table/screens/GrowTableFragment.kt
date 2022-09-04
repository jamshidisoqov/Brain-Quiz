package uz.gita.robo_brain.presentation.ui.grow_table.screens

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
import uz.gita.robo_brain.databinding.FragmentGrowTableBinding
import uz.gita.robo_brain.presentation.ui.grow_table.view_model.GrowTableViewModel
import uz.gita.robo_brain.presentation.ui.grow_table.view_model.impl.GrowTableViewModelImpl
import uz.gita.robo_brain.presentation.ui.puzzle_2048.screens.dialog.GameOverDialog
import uz.gita.robo_brain.presentation.ui.puzzle_2048.screens.dialog.WinGameDialog
import uz.gita.robo_brain.utils.getTimeFormat

class GrowTableFragment : Fragment(R.layout.fragment_grow_table) {

    private val viewModel: GrowTableViewModel by viewModels<GrowTableViewModelImpl>()

    private val viewBinding: FragmentGrowTableBinding by viewBinding()

    private val numberList = ArrayList<TextView>(16)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.finishLiveData.observe(this, winnerObserver)
        viewModel.gameOverLiveData.observe(this, gameOverObserver)
        viewModel.bestResultLiveData.observe(this, bestTimeObserver)
        viewModel.backLiveData.observe(this, backObserver)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        viewModel.numberListLiveData.observe(viewLifecycleOwner, numberListObserver)
        viewModel.numberLiveData.observe(viewLifecycleOwner, numberObserver)
        viewModel.timerLiveData.observe(viewLifecycleOwner, timerObserver)
        viewBinding.imageBack.setOnClickListener {
            viewModel.back()
        }
    }

    private val numberListObserver = Observer<List<Int>> {
        for (i in numberList.indices) {
            numberList[i].text = it[i].toString()
        }
    }

    @SuppressLint("SetTextI18n")
    private val numberObserver = Observer<Int> {
        viewBinding.tvFindNumber.apply {
            text = "Find Number $it"
            startAnimZoomIn(this)
        }
    }

    private val timerObserver = Observer<Int> {
        viewBinding.tvCurrentTime.text = it.getTimeFormat()
    }

    private val bestTimeObserver = Observer<Int> {
        viewBinding.tvBestTime.text = it.getTimeFormat()
    }


    private fun initView() {
        val container = viewBinding.headerContainer
        val size = container.childCount
        for (i in 0 until size) {
            val group: ViewGroup = container.getChildAt(i) as ViewGroup
            for (j in 0 until size) {
                val view = group.getChildAt(j) as TextView
                view.setOnClickListener {
                    startAnimZoomIn(it)
                    viewModel.check((it as TextView).text.toString().toInt())
                    val player = MediaPlayer.create(requireContext(), R.raw.click)
                    player.start()
                }
                numberList.add(view)
            }
        }
    }

    private fun startAnimZoomIn(view: View) {
        YoYo.with(Techniques.ZoomIn)
            .delay(0)
            .duration(100)
            .playOn(view)
    }

    private val gameOverObserver = Observer<Unit> {
        val dialog = GameOverDialog(requireContext())
        dialog.setFinishListener {
            dialog.dismiss()
            findNavController().navigateUp()
        }
        dialog.setRetryListener {
            dialog.dismiss()
            viewModel.newGame()
        }
        dialog.show()
    }

    private val winnerObserver = Observer<Int> {
        val dialog = WinGameDialog(requireContext())
        dialog.setDismissListener {
            dialog.dismiss()
            findNavController().navigateUp()

        }
        dialog.setRetryClickListener {
            dialog.dismiss()
            viewModel.newGame()
        }
        dialog.show()
    }

    private val backObserver = Observer<Unit> {
        findNavController().navigateUp()
    }

}