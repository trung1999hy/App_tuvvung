package com.tmt.vuaghepchu.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tmt.vuaghepchu.databinding.FragmentResultScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import com.tmt.vuaghepchu.ui.SharedViewModel
import com.tmt.vuaghepchu.ui.base.BaseFragment
import com.tmt.vuaghepchu.utils.Preference

@AndroidEntryPoint
class ResultScreenFragment : BaseFragment() {

    private lateinit var binding: FragmentResultScreenBinding
    private val viewModel: SharedViewModel by viewModels()
    private var preference: Preference? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    private fun initData() {
        preference = Preference.buildInstance(requireContext())
        binding.resultCardViewTop.levelViewValue.text = viewModel.getLevel().toString()
        binding.resultCardViewTop.coinViewValue.text = preference?.getValueCoin().toString()
    }

    private fun initView() {
        binding.resultBtnNext.setOnClickListener {
            if (viewModel.isMaxLevel())
                findNavController().navigate(ResultScreenFragmentDirections.actionResultFragmentToHomeFragment())
            findNavController().navigate(ResultScreenFragmentDirections.actionResultFragmentToGameFragment())
        }
        binding.resultBtnQuit.setOnClickListener {
            findNavController().navigate(ResultScreenFragmentDirections.actionResultFragmentToHomeFragment())
        }
        binding.resultCardViewTop.coinViewContainer.setOnClickListener {
            navigateChargeCoin()
        }
    }
}

