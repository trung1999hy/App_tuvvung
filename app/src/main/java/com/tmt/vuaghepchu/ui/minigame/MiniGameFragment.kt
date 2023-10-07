package com.tmt.vuaghepchu.ui.minigame

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.tmt.vuaghepchu.R
import com.tmt.vuaghepchu.databinding.FragmentMinigameBinding
import com.tmt.vuaghepchu.ui.base.BaseFragment
import com.tmt.vuaghepchu.utils.Preference
import kotlin.random.Random

class MiniGameFragment : BaseFragment(), View.OnClickListener {

    companion object {
        private const val KEY_COIN_PLAY = 1
    }

    private lateinit var binding: FragmentMinigameBinding
    private var numberOption: Int = 0
    private var numberResult: Int = 0
    private val arrOption = arrayListOf(1, 2, 3)
    private var countDownTimer: CountDownTimer? = null
    private var preference: Preference? = null

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
        preference = Preference.buildInstance(requireContext())
        binding.mgCardViewTop.levelViewTitle.text = "Lượt chơi:"
        binding.mgCardViewTop.coinViewValue.text = (preference?.getValueCoin() ?: 0).toString()
        addTimePlay(0)
    }

    private fun initAction() {
        binding.hammerSelection.setOnClickListener(this)
        binding.bagChoice.setOnClickListener(this)
        binding.scissorsSelection.setOnClickListener(this)
    }

    private fun addTimePlay(number: Int) {
        val numberPlay = getTimePlayRemain()
        val currentTime = if (numberPlay == 0) 0
        else numberPlay + number
        preference?.setValueMiniGame(currentTime)
        binding.mgCardViewTop.levelViewValue.text = currentTime.toString()
    }

    private fun getTimePlayRemain(): Int {
        return preference?.getValueMiniGame() ?: 0
    }

    private fun random() {
        countDownTimer = object : CountDownTimer(1500, 100) {
            override fun onTick(millisUntilFinished: Long) {
                configRandom()
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                binding.hammerSelection.setBackgroundColor(Color.parseColor("#54A2DF"))
                binding.bagChoice.setBackgroundColor(Color.parseColor("#54A2DF"))
                binding.scissorsSelection.setBackgroundColor(Color.parseColor("#54A2DF"))

                val isWin = (numberOption == 1 && numberResult == 3) ||
                        (numberOption == 2 && numberResult == 1) ||
                        (numberOption == 3 && numberResult == 2)

                when {
                    numberOption == numberResult -> showSnackBar(binding.root, "Hòa!")

                    isWin -> {
                        preference?.getValueCoin()?.let {
                            preference?.setValueCoin(it + KEY_COIN_PLAY)
                        }
                        binding.mgCardViewTop.coinViewValue.text =
                            (preference?.getValueCoin() ?: 0).toString()
                        showSnackBar(binding.root, "Bạn đã thắng! Cộng +1 vàng!")
                    }

                    else -> showSnackBar(binding.root, "Thua mất rồi!")

                }
                numberOption = 0
                binding.scissorsSelection.isClickable = true
                binding.hammerSelection.isClickable = true
                binding.bagChoice.isClickable = true
            }
        }.start()
    }

    @SuppressLint("DiscouragedApi")
    fun configRandom() {
        numberResult = arrOption.random()
        val str = resources.getIdentifier(
            "icgame_$numberResult",
            "drawable",
            requireActivity().packageName
        )
        binding.imgResult.setImageResource(str)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.scissors_selection -> {
                isCheckScore(binding.scissorsSelection, 1)
            }

            R.id.hammer_selection -> {
                isCheckScore(binding.hammerSelection, 2)
            }

            R.id.bag_choice -> {
                isCheckScore(binding.bagChoice, 3)
            }
        }
    }

    private fun isCheckScore(icon: ImageView, nbOption: Int) {
        numberOption = nbOption
        if (getTimePlayRemain() >= 1) {
            binding.scissorsSelection.isClickable = false
            binding.hammerSelection.isClickable = false
            binding.bagChoice.isClickable = false
            icon.setBackgroundColor(Color.parseColor("#89FF00"))
            random()
            addTimePlay(-1)
        } else {
            showSnackBar(binding.root, "Bạn đã hết lượt chơi hôm nay!")
        }
    }
}