package fus.com.vuatuvung.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import fus.com.vuatuvung.databinding.FragmentHomeBinding
import fus.com.vuatuvung.ui.base.BaseFragment
import androidx.navigation.fragment.findNavController
import fus.com.vuatuvung.ui.SharedViewModel
import fus.com.vuatuvung.utils.Preference

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction()
        initData()

        binding.homeIvLogo.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToGameFragment())
        }
    }

    private fun initData() {
        if (viewModel.getInitData().isEmpty()) {
            viewModel.setInitData()
        }
    }

    private fun initAction() {
        binding.homeBtnPlay.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToGameFragment())
        }
    }

}