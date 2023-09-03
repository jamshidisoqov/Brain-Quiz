package uz.uni_team.robo_brain.presentation.ui.sort_math.screens

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.uni_team.robo_brain.R
import uz.uni_team.robo_brain.databinding.FragmentSortMathBinding
import uz.uni_team.robo_brain.presentation.ui.sort_math.adapters.SortedAdapter
import uz.uni_team.robo_brain.presentation.ui.sort_math.view_model.SortMathViewModel
import uz.uni_team.robo_brain.presentation.ui.sort_math.view_model.impl.SortMathViewModelImpl
import uz.uni_team.robo_brain.repository.models.SortedMath

class SortMathFragment : Fragment(R.layout.fragment_sort_math) {


    private val viewModel: SortMathViewModel by viewModels<SortMathViewModelImpl>()

    private val adapter: SortedAdapter by lazy {
        SortedAdapter()
    }

    private lateinit var player: MediaPlayer

    private val binding: FragmentSortMathBinding by viewBinding()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.questionLiveData.observe(this, questionObserver)
        viewModel.finishLiveData.observe(this, finishObserver)
        viewModel.timer.observe(this, timerObserver)
        viewModel.questionCountLiveData.observe(this, questionCountObserver)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        player = MediaPlayer.create(requireContext(), R.raw.sound)
        binding.listSortedMath.adapter = adapter

        binding.checkContainer.setOnClickListener {
            viewModel.check(adapter.list)
            binding.progressHorizontalSortedMath.progress = 100f
        }
        val touchHelper =
            object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    adapter.onMove(
                        viewHolder.absoluteAdapterPosition,
                        target.absoluteAdapterPosition
                    )
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                }

            }
        val helper = ItemTouchHelper(touchHelper)
        helper.attachToRecyclerView(binding.listSortedMath)
        viewModel.musicLiveData.observe(viewLifecycleOwner,musicObserver)

    }


    private val questionObserver = Observer<List<SortedMath>> {
        adapter.submitList(it.shuffled())
    }

    private val finishObserver = Observer<Int> {
        findNavController().navigate(SortMathFragmentDirections.actionSortMathFragmentToSortedMathResultFragment(it))
    }

    @SuppressLint("SetTextI18n")
    private val questionCountObserver = Observer<Int> {
        binding.tvQuestion.text = "Question:$it"
    }

    private val timerObserver = Observer<Int> {
        binding.progressHorizontalSortedMath.progress = it * 3.33f
        binding.tvTime.text = it.toString()
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