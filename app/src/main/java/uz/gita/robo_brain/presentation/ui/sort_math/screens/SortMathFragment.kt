package uz.gita.robo_brain.presentation.ui.sort_math.screens

import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tapadoo.alerter.Alerter
import uz.gita.robo_brain.R
import uz.gita.robo_brain.databinding.FragmentSortMathBinding
import uz.gita.robo_brain.presentation.ui.sort_math.adapters.SortedAdapter
import uz.gita.robo_brain.presentation.ui.sort_math.view_model.SortMathViewModel
import uz.gita.robo_brain.presentation.ui.sort_math.view_model.impl.SortMathViewModelImpl
import uz.gita.robo_brain.repository.models.SortedMath

class SortMathFragment : Fragment(R.layout.fragment_sort_math) {


    private val viewModel: SortMathViewModel by viewModels<SortMathViewModelImpl>()

    private val adapter: SortedAdapter by lazy {
        SortedAdapter()
    }

    private val binding: FragmentSortMathBinding by viewBinding()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.questionLiveData.observe(this, questionObserver)
        viewModel.finishLiveData.observe(this, finishObserver)

    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.chrTime.isCountDown=true
        binding.chrTime.base = SystemClock.elapsedRealtime()+10000
        binding.chrTime.start()
        binding.listSortedMath.adapter = adapter

        binding.checkContainer.setOnClickListener {
            viewModel.check(adapter.list)
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

    }



    private val questionObserver = Observer<List<SortedMath>> {
        adapter.submitList(it)
    }

    private val finishObserver = Observer<Int> {
        Alerter.create(requireActivity())
            .setText("Finished")
            .setTitle("You're Result $it")
            .show()
    }

    private val gameOverObserver = Observer<Unit> {

    }

    private val questionCountObserver = Observer<Int> {

    }


}