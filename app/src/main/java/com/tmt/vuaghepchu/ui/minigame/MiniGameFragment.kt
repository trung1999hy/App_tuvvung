package com.tmt.vuaghepchu.ui.minigame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.tmt.vuaghepchu.ui.minigame.adapter.GameAdapter
import com.tmt.vuaghepchu.databinding.FragmentMinigameBinding
import com.tmt.vuaghepchu.ui.minigame.model.GameModel
import com.tmt.vuaghepchu.ui.minigame.network.Repository
import com.tmt.vuaghepchu.ui.base.BaseFragment


class MiniGameFragment : BaseFragment() {
    private lateinit var binding: FragmentMinigameBinding
    private var mAdapter: GameAdapter? = null
    private var sportGame: LiveData<ArrayList<GameModel>>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMinigameBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initAction()
    }

    private fun initData() {
        val repository = Repository()
        mAdapter = GameAdapter(requireActivity(), itemClick = {
            navigateMiniGame(it.linkGame)
        })

        sportGame = repository.getSportGame()

        sportGame?.observe(viewLifecycleOwner) { gameModels: ArrayList<GameModel>? ->
            mAdapter?.submitList(gameModels as ArrayList<GameModel>)
            binding.ProgressBarLoading.visibility = View.GONE
        }
    }

    private fun initAction() {
        binding.RcvGameRacing.layoutManager =
            StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
        binding.RcvGameRacing.adapter = mAdapter
    }
}