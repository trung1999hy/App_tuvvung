package fus.com.vuatuvung.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import fus.com.vuatuvung.databinding.FragmentProfileBinding
import fus.com.vuatuvung.databinding.FragmentResultBinding
import fus.com.vuatuvung.ui.SharedViewModel
import fus.com.vuatuvung.ui.base.BaseFragment
import fus.com.vuatuvung.ui.home.HomeFragmentDirections
import fus.com.vuatuvung.utils.Preference

@AndroidEntryPoint
class ResultFragment : BaseFragment() {

    private lateinit var binding: FragmentResultBinding
    private val viewModel: SharedViewModel by viewModels()
    private var preference: Preference? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    private fun initData() {
        preference = Preference.buildInstance(requireContext())
        binding.resultLevelValue.text = viewModel.getLevel().toString()
        binding.resultCoinValue.text = preference?.getValueCoin().toString()
    }

    private fun initView() {
        binding.resultBtnNext.setOnClickListener {
            findNavController().navigate(ResultFragmentDirections.actionResultFragmentToGameFragment())
        }
        binding.resultBtnQuit.setOnClickListener {
            findNavController().navigate(ResultFragmentDirections.actionResultFragmentToHomeFragment())
        }
        binding.resultCoin.setOnClickListener {
            navigateChargeCoin()
        }
        binding.resultCoinValue.setOnClickListener {
            navigateChargeCoin()
        }
    }

}

