package uz.uni_team.robo_brain.presentation.ui.settings

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.drjacky.imagepicker.ImagePicker
import com.github.drjacky.imagepicker.constant.ImageProvider
import uz.uni_team.robo_brain.R
import uz.uni_team.robo_brain.databinding.FragmentSettingsBinding
import uz.uni_team.robo_brain.presentation.ui.settings.dialogs.ChangeNameDialog
import uz.uni_team.robo_brain.presentation.ui.settings.impl.SettingsViewModelImpl
import uz.uni_team.robo_brain.utils.setLocalImage

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var mProfileUri: Uri? = null

    private val viewModel: SettingsViewModel by viewModels<SettingsViewModelImpl>()

    private val binding: FragmentSettingsBinding by viewBinding()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.changeNameLiveData.observe(this, changeNameObserver)
        viewModel.changeImageLiveData.observe(this, changeImageObserver)
        viewModel.backLiveData.observe(this, backListener)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.imageContainer.setOnClickListener {
            viewModel.changeImage()
        }
        binding.imageBack.setOnClickListener {
            viewModel.backClick()
        }
        binding.nameContainer.setOnClickListener {
            viewModel.changeName()
        }
        binding.switchMusic.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setMusic(isChecked)
        }
        viewModel.nameLiveData.observe(viewLifecycleOwner, nameObserver)
        viewModel.imageLiveData.observe(viewLifecycleOwner, imageObserver)
        viewModel.musicLiveData.observe(viewLifecycleOwner, musicObserver)
    }

    private val nameObserver = Observer<String> {
        binding.tvName.text = it
    }

    private val imageObserver = Observer<String> {
        if (it == "image") {
            binding.yourImage.setImageResource(R.drawable.user)
        } else {
            binding.yourImage.setLocalImage(Uri.parse(it), true)
        }
    }

    private val musicObserver = Observer<Boolean> {
        binding.switchMusic.isChecked = it
    }

    private val changeNameObserver = Observer<Unit> {
        val dialog = ChangeNameDialog(requireContext(), viewModel.nameLiveData.value!!)
        dialog.show()
        dialog.setChangeListener {
            viewModel.setName(it)
        }
    }

    private val backListener = Observer<Unit> {
        findNavController().navigateUp()
    }


    private val profileLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data!!
                viewModel.setImage(uri.toString())
                mProfileUri = uri
                binding.yourImage.setLocalImage(uri, true)
            }
        }

    private val changeImageObserver = Observer<Unit> {
        ImagePicker.with(requireActivity())
            .crop()
            .cropOval()
            .maxResultSize(512, 512, true)
            .provider(ImageProvider.BOTH)
            .createIntentFromDialog {
                profileLauncher.launch(it)
            }

    }


}