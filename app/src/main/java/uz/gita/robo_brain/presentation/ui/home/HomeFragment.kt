package uz.gita.robo_brain.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.robo_brain.MainActivity
import uz.gita.robo_brain.R
import uz.gita.robo_brain.databinding.FragmentHomeBinding
import uz.gita.robo_brain.presentation.ui.home.view_model.HomeViewModel
import uz.gita.robo_brain.presentation.ui.home.view_model.impl.HomeViewModelImpl
import uz.gita.robo_brain.utils.Constants

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding()

    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()

    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openMainLiveData.observe(this, openMainObserver)
        viewModel.openPlayMarket.observe(this, openPlayMarketObserver)
        viewModel.openSettingLiveData.observe(this, openSettingsObserver)
        viewModel.openShareAppLiveData.observe(this, openShareObserver)
        viewModel.openAboutLiveData.observe(this, openAboutLiveData)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.imageMenu.setOnClickListener {
            viewModel.openAbout()
        }
        binding.imagePlay.setOnClickListener {
            viewModel.openMain()
        }
        binding.imageSettings.setOnClickListener {
            viewModel.openSettings()
        }
        binding.imageShare.setOnClickListener {
            viewModel.openShareApp()
        }
        binding.imageSupport.setOnClickListener {
            viewModel.openPlayMarket()
        }
    }

    private val openMainObserver = Observer<Unit> {
        navController.navigate(HomeFragmentDirections.actionHomeFragmentToMenuFragment())
    }
    private val openPlayMarketObserver = Observer<Unit> {
        Constants.goToPlayMarket(requireActivity() as MainActivity)
    }
    private val openSettingsObserver = Observer<Unit> {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSettingsFragment())
    }

    private val openShareObserver = Observer<Unit> {
        Constants.shareApp(requireActivity() as MainActivity)
    }
    private val openAboutLiveData = Observer<Unit> {
        //findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAboutFragment())
    }

}