package fus.com.vuatuvung.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import fus.com.vuatuvung.databinding.FragmentSettingsBinding
import fus.com.vuatuvung.ui.SharedViewModel
import fus.com.vuatuvung.ui.base.BaseFragment

@AndroidEntryPoint
class SettingsFragment : BaseFragment() {

    private lateinit var binding: FragmentSettingsBinding
    private val viewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.settingSoundSwitch.isChecked = viewModel.isEnableSound()
        binding.settingSoundFXSwitch.isChecked = viewModel.isEnableSoundFX()

        binding.settingSoundSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.setEnableSound(isChecked)
            if (isChecked) {
                viewModel.playClickWithoutCheck()
            }
        }
        binding.settingSoundFXSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.setEnableSoundFX(isChecked)
            if (isChecked) {
                viewModel.playClickWithoutCheck()
            }
        }
    }
}