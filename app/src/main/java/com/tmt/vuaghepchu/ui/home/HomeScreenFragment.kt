package com.tmt.vuaghepchu.ui.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import com.tmt.vuaghepchu.ui.base.BaseFragment
import androidx.navigation.fragment.findNavController
import com.tmt.vuaghepchu.databinding.FragmentHomeScreenBinding
import com.tmt.vuaghepchu.ui.SharedViewModel

@AndroidEntryPoint
class HomeScreenFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeScreenBinding

    private val viewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction()
        initData()

        binding.homeIvLogo.setOnClickListener {
            if (viewModel.isMaxLevel()) return@setOnClickListener
            findNavController().navigate(HomeScreenFragmentDirections.actionHomeFragmentToGameFragment())
        }
    }

    private fun initData() {
        if (viewModel.getInitData().isEmpty()) {
            viewModel.setInitData()
        }
    }

    private fun initAction() {
        binding.homeBtnPlay.setOnClickListener {
            findNavController().navigate(HomeScreenFragmentDirections.actionHomeFragmentToGameFragment())
        }

        binding.homeBtnMiniGame.setOnClickListener {
            findNavController().navigate(HomeScreenFragmentDirections.actionHomeFragmentToMiniGameFragment())
        }
    }
}