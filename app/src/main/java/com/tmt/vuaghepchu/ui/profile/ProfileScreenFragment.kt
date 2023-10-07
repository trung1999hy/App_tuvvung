package com.tmt.vuaghepchu.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tmt.vuaghepchu.databinding.FragmentProfileScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import com.tmt.vuaghepchu.ui.SharedViewModel
import com.tmt.vuaghepchu.ui.base.BaseFragment
import com.tmt.vuaghepchu.utils.Preference

@AndroidEntryPoint
class ProfileScreenFragment : BaseFragment() {

    private lateinit var binding: FragmentProfileScreenBinding
    private val viewModel: SharedViewModel by viewModels()
    private var preference: Preference? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileScreenBinding.inflate(inflater)
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

