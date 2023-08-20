package fus.com.vuatuvung.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import fus.com.vuatuvung.databinding.FragmentProfileBinding
import fus.com.vuatuvung.ui.SharedViewModel
import fus.com.vuatuvung.ui.base.BaseFragment
import fus.com.vuatuvung.utils.Preference

@AndroidEntryPoint
class ProfileFragment : BaseFragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: SharedViewModel by viewModels()
    private var preference: Preference? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {
        binding.profileTvCoin.setOnClickListener {
            navigateChargeCoin()
        }
        binding.profileCoin.setOnClickListener {
            navigateChargeCoin()
        }
    }

    private fun initData() {
        preference = Preference.buildInstance(requireContext())
        binding.profileTvLevelUnlocked.text = viewModel.getLevel().toString()
        binding.profileTvCoin.text = preference?.getValueCoin().toString()
        binding.profileProgressBar.progress = viewModel.getProgress()
        binding.profileTvProgressPercent.text = "${viewModel.getProgress()}%"
    }

}

