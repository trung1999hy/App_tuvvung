package com.tmt.vuaghepchu.ui.minigame

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.tmt.vuaghepchu.R
import com.tmt.vuaghepchu.databinding.FragmentMinigameBinding
import com.tmt.vuaghepchu.ui.base.BaseFragment
import kotlin.random.Random


class MiniGameFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentMinigameBinding
    private var score: Int = -1
    private var count: Int = 0
    private var numberOption: Int = 0
    private var numberResult: Int = 0
    private val arrOption = arrayListOf(1, 2, 3)
    private var countDownTimer: CountDownTimer? = null

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
        initAction()
    }


    @SuppressLint("SetTextI18n")
    private fun initAction() {
        val settings: SharedPreferences =
            requireActivity().getSharedPreferences("Score", Context.MODE_PRIVATE)
        score = settings.getInt("score", 100)
        getScore(requireActivity(), binding.tvScore)
        binding.hammerSelection.setOnClickListener(this)
        binding.bagChoice.setOnClickListener(this)
        binding.scissorsSelection.setOnClickListener(this)
        binding.imgRefresh.setOnClickListener(this)
    }

    private fun saveScore(context: Context, score: Int) {
        val settings: SharedPreferences =
            context.getSharedPreferences("Score", Context.MODE_PRIVATE)
        val edit: SharedPreferences.Editor = settings.edit()
        edit.putInt("score", score)
        edit.apply()
    }

    @SuppressLint("SetTextI18n")
    private fun getScore(context: Context, tvScore: TextView) {
        val settings: SharedPreferences =
            context.getSharedPreferences("Score", Context.MODE_PRIVATE)
        tvScore.text = "Score: ${settings.getInt("score", 100)}"
    }

    private fun random(context: Context) {
        countDownTimer = object : CountDownTimer(1000, 60) {
            override fun onTick(millisUntilFinished: Long) {
                configRandom()
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                binding.hammerSelection.setBackgroundColor(Color.parseColor("#54A2DF"))
                binding.bagChoice.setBackgroundColor(Color.parseColor("#54A2DF"))
                binding.scissorsSelection.setBackgroundColor(Color.parseColor("#54A2DF"))
                if (numberResult > numberOption) {
                    Toast.makeText(context, "Bạn đã thua và bị trừ -15 điểm", Toast.LENGTH_SHORT)
                        .show()
                    score -= 15
                    binding.tvScore.text = "Score: $score"
                } else if (numberResult < numberOption) {
                    score += 20
                    binding.tvScore.text = "Score: $score"
                    Toast.makeText(
                        context,
                        "Chúc mừng bạn đã thắng và được +20 điểm",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(context, "Hòa", Toast.LENGTH_SHORT).show()
                }
                numberOption = 0
                if (score <= 0) {
                    score = 0
                    binding.tvScore.text = "Score: $score"
                    Toast.makeText(
                        context,
                        "Bạn đã hết điểm vui lòng bấm chơi lại",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.imgRefresh.visibility = View.VISIBLE
                }
                binding.scissorsSelection.isClickable = true
                binding.hammerSelection.isClickable = true
                binding.bagChoice.isClickable = true
                saveScore(context, score)
            }
        }.start()
    }

    @SuppressLint("DiscouragedApi")
    fun configRandom() {
        count = Random.nextInt(arrOption.size)
        numberResult = (count + 1)
        val str = resources.getIdentifier(
            "icgame_" + arrOption[count],
            "drawable",
            requireActivity().packageName
        )
        binding.imgResult.setImageResource(str)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.hammer_selection -> {
                isCheckScore(binding.hammerSelection, 2)
            }

            R.id.scissors_selection -> {
                isCheckScore(binding.scissorsSelection, 3)
            }

            R.id.bag_choice -> {
                isCheckScore(binding.bagChoice, 1)
            }

            R.id.img_refresh -> {
                saveScore(requireActivity(), 100)
                getScore(requireActivity(), binding.tvScore)
            }
        }
    }

    private fun isCheckScore(icon: ImageView, nbOption: Int) {
        numberOption = nbOption
        if (score in 1..1000000) {
            binding.scissorsSelection.isClickable = false
            binding.hammerSelection.isClickable = false
            binding.bagChoice.isClickable = false
            icon.setBackgroundColor(Color.parseColor("#89FF00"))
            random(requireActivity())
        } else if (score <= 0) {
            score = 0
            binding.tvScore.text = "Score: $score"
            Toast.makeText(
                requireActivity(),
                "Bạn đã hết điểm vui lòng bấm chơi lại",
                Toast.LENGTH_SHORT
            ).show()
            binding.imgRefresh.visibility = View.VISIBLE
        }
    }
}