package uz.uni_team.robo_brain.presentation.ui.menu.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.uni_team.robo_brain.MainActivity
import uz.uni_team.robo_brain.databinding.DialogMenuBottomBinding
import uz.uni_team.robo_brain.presentation.ui.menu.MenuFragmentDirections
import uz.uni_team.robo_brain.utils.Constants
import uz.uni_team.task_app.domain.presenter.main.BottomMenuViewModel
import uz.uni_team.task_app.domain.presenter.main.impl.BottomMenuViewModelImpl

// Created by Jamshid Isoqov an 8/12/2022
class BottomMenuDialog : BottomSheetDialogFragment() {


    private lateinit var binding: DialogMenuBottomBinding

    private val viewModel: BottomMenuViewModel by viewModels<BottomMenuViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DialogMenuBottomBinding.inflate(inflater, container, false)
        viewModel.aboutUsLiveData.observe(this, aboutObserver)
        viewModel.shareAppLiveData.observe(this, shareAppObserver)
        viewModel.supportUsLiveData.observe(this, supportObserver)

        binding.apply {
            tvAboutUs.setOnClickListener { viewModel.aboutClick() }
            tvSupportUs.setOnClickListener { viewModel.supportClick() }
            tvShareApp.setOnClickListener { viewModel.shareClick() }
        }
        return binding.root
    }

    private val supportObserver = Observer<Unit> {
        dismiss()
        Constants.goToPlayMarket(activity as MainActivity)
    }

    private val shareAppObserver = Observer<Unit> {
        dismiss()
        Constants.shareApp(requireActivity() as MainActivity)
    }

    private val aboutObserver = Observer<Unit> {
        dismiss()
        findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToAboutFragment())
    }

}