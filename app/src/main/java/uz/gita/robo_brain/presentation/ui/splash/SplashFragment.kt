package uz.gita.robo_brain.presentation.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.robo_brain.R
import uz.gita.robo_brain.databinding.FragmentSplashBinding
import uz.gita.robo_brain.presentation.ui.splash.view_model.SplashViewModel
import uz.gita.robo_brain.presentation.ui.splash.view_model.impl.SplashViewModelImpl

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openMainScreen.observe(this, openMainObserver)
    }

    private val openMainObserver = Observer<Unit> {
        //find
    }

}