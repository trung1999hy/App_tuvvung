package fus.com.vuatuvung.ui.game

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.postDelayed
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.jinatonic.confetti.CommonConfetti
import dagger.hilt.android.AndroidEntryPoint
import fus.com.vuatuvung.databinding.FragmentGameBinding
import fus.com.vuatuvung.ui.SharedViewModel
import fus.com.vuatuvung.ui.base.BaseFragment
import fus.com.vuatuvung.ui.chargecoin.ChargeCoinActivity
import fus.com.vuatuvung.ui.game.model.ItemViewData
import fus.com.vuatuvung.ui.game.model.Location
import fus.com.vuatuvung.utils.Constants
import fus.com.vuatuvung.utils.Preference


@AndroidEntryPoint
class GameFragment : BaseFragment() {

    private lateinit var binding: FragmentGameBinding
    private val viewModel: SharedViewModel by viewModels()
    private var preference: Preference? = null

    private var mResult: String = ""
    private var listTextShuffle: ArrayList<Char> = arrayListOf()
    private var listTextTarget: ArrayList<Char> = arrayListOf()
    private var mCurrentLevel: Int = 0
    private var mListData: ArrayList<String> = arrayListOf()

    private val alInitLocation: ArrayList<Location> = arrayListOf()
    private val alViewAdded: ArrayList<BoxView> = arrayListOf()
    private var mapTargetView: HashMap<Int, ItemViewData> = HashMap()
    private val alViewTop: ArrayList<BoxView> = arrayListOf()
    private val alViewBottom: ArrayList<BoxView> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        initAction()
    }

    private fun initData() {
        preference = Preference.buildInstance(requireContext())
        if (viewModel.getLevel() == 0) {
            viewModel.addLevel(viewModel.getLevel() + 1)
        }
        mListData = viewModel.getInitData()
        mCurrentLevel = viewModel.getLevel()
        binding.gameLevelValue.text = viewModel.getLevel().toString()
        binding.gameCoinValue.text = preference?.getValueCoin().toString()
        mResult = mListData.getOrNull(mCurrentLevel) ?: Constants.KEY_DEFAULT_TEXT
        initValue(mResult)
    }

    private fun initValue(value: String) {
        listTextShuffle.clear()
        listTextTarget.clear()

        value.forEachIndexed { i, element ->
            listTextShuffle.add(element)
            listTextTarget.add(element)
            mapTargetView[i]?.correctValue = element.toString()
            mapTargetView[i]?.canShowSuggestion = true
        }

        listTextShuffle.shuffle()
        val output: StringBuilder = StringBuilder(listTextShuffle.size)
        listTextShuffle.forEachIndexed { index, v ->
            (alViewBottom.getOrNull(index) as BoxView).apply {
                output.append(v).append("/")
                setText(v.toString())
                visibility = View.VISIBLE
            }
            (alViewTop.getOrNull(index) as BoxView).apply {
                setText("")
                visibility = View.VISIBLE
            }
            val mTextShuffle = if (output.isNotEmpty())
                output.substring(0, output.length - 1)
            else ""
            binding.gameTvContent.text = mTextShuffle
        }
    }

    private fun initAction() {
        binding.bvBot1.setOnClickListener {
            viewModel.playClick()
            handleBeforeMove(it as BoxView)
        }
        binding.bvBot2.setOnClickListener {
            viewModel.playClick()
            handleBeforeMove(it as BoxView)
        }
        binding.bvBot3.setOnClickListener {
            viewModel.playClick()
            handleBeforeMove(it as BoxView)
        }
        binding.bvBot4.setOnClickListener {
            viewModel.playClick()
            handleBeforeMove(it as BoxView)
        }
        binding.bvBot5.setOnClickListener {
            viewModel.playClick()
            handleBeforeMove(it as BoxView)
        }
        binding.bvBot6.setOnClickListener {
            viewModel.playClick()
            handleBeforeMove(it as BoxView)
        }
        binding.bvBot7.setOnClickListener {
            viewModel.playClick()
            handleBeforeMove(it as BoxView)
        }
        binding.bvBot8.setOnClickListener {
            viewModel.playClick()
            handleBeforeMove(it as BoxView)
        }
        binding.bvSuggest.setOnClickListener {
            viewModel.playClick()
            checkCoinSuggest()
        }
        binding.gameCoinValue.setOnClickListener {
            navigateChargeCoin()
        }
        binding.gameCoin.setOnClickListener {
            navigateChargeCoin()
        }
    }

    private fun checkCoinSuggest() {
        var currentCoin = preference?.getValueCoin() ?: 0
        if (currentCoin < 5) {
            Toast.makeText(requireContext(), "Không đủ vàng!", Toast.LENGTH_SHORT).show()
            navigateChargeCoin()
        } else {
            handleShowSuggest()
            currentCoin -= 5
        }

        preference?.setValueCoin(currentCoin)
        binding.gameCoinValue.text = currentCoin.toString()
    }

    private fun handleShowSuggest() {
        val viewShow = mapTargetView.asSequence().firstOrNull { it.value.canShowSuggestion }
        if (viewShow == null) {
            alViewAdded.forEachIndexed { index, it ->
                val textCorrect = listTextTarget.getOrNull(index) ?: ""
                if (it.getTextBox() != textCorrect) {
                    mapTargetView[index]?.let {
                        it.canShowSuggestion = false
                        it.view.setText(textCorrect.toString())
                    }
                    return
                }
            }
        } else {
            viewShow.value.apply {
                this.view.setText(viewShow.value.correctValue)
                this.canShowSuggestion = false
            }
        }
    }

    private fun initView() {
        mapTargetView = hashMapOf(
            0 to ItemViewData(binding.bv1, null, isAdded = false, "", canShowSuggestion = false),
            1 to ItemViewData(binding.bv2, null, isAdded = false, "", canShowSuggestion = false),
            2 to ItemViewData(binding.bv3, null, isAdded = false, "", canShowSuggestion = false),
            3 to ItemViewData(binding.bv4, null, isAdded = false, "", canShowSuggestion = false),
            4 to ItemViewData(binding.bv5, null, isAdded = false, "", canShowSuggestion = false),
            5 to ItemViewData(binding.bv6, null, isAdded = false, "", canShowSuggestion = false),
            6 to ItemViewData(binding.bv7, null, isAdded = false, "", canShowSuggestion = false),
            7 to ItemViewData(binding.bv8, null, isAdded = false, "", canShowSuggestion = false),
        )

        alViewTop.addAll(
            arrayListOf(
                binding.bv1,
                binding.bv2,
                binding.bv3,
                binding.bv4,
                binding.bv5,
                binding.bv6,
                binding.bv7,
                binding.bv8
            )
        )
        alViewBottom.addAll(
            arrayListOf(
                binding.bvBot1,
                binding.bvBot2,
                binding.bvBot3,
                binding.bvBot4,
                binding.bvBot5,
                binding.bvBot6,
                binding.bvBot7,
                binding.bvBot8
            )
        )

        alViewTop.forEach { it.visibility = View.GONE }
        alViewBottom.forEach { it.visibility = View.GONE }
    }

    private fun handleBeforeMove(view: BoxView) {
        if (alViewAdded.contains(view)) {
            removeViewAdded(view)
            moveView(view)
            alViewAdded.remove(view)
        } else {
            alViewAdded.add(view)
            val index = findIndexViewNotAdded()
            index?.let {
                mapTargetView[index]?.let {
                    it.viewAdded = view
                    it.isAdded = true
                    it.canShowSuggestion = false
                    moveView(view, it.view)
                }
            }
            checkResult()
        }
    }

    private fun findIndexViewNotAdded(): Int? {
        return mapTargetView.asSequence().firstOrNull { !it.value.isAdded }?.key
    }

    private fun removeViewAdded(view: BoxView) {
        val value = mapTargetView.asSequence().firstOrNull { it.value.viewAdded == view }
        value?.value?.isAdded = false
        value?.value?.viewAdded = null
        (value?.value?.view as? BoxView)?.let {
            if (it.getTextBox() == "") {
                value.value.canShowSuggestion = true
            }
        }
    }

    private fun moveView(view: View) {
        val oldLocation = alInitLocation.find { it.id == view.id }
        view.animate()
            .x(oldLocation?.xValue ?: 0f)
            .y(oldLocation?.yValue ?: 0f)
            .setDuration(200)
            .start()
    }

    private fun moveView(viewToBeMoved: View, targetView: View) {
        val targetX: Float =
            targetView.x + targetView.width / 2 - viewToBeMoved.width / 2
        val targetY: Float =
            targetView.y + targetView.height / 2 - viewToBeMoved.height / 2
        if (alInitLocation.find { it.id == viewToBeMoved.id } == null)
            alInitLocation.add(Location(viewToBeMoved.id, viewToBeMoved.x, viewToBeMoved.y))
        viewToBeMoved.animate()
            .x(targetX)
            .y(targetY)
            .setDuration(200)
            .start()
    }

    private fun checkResult() {
        var current = ""
        mapTargetView.asSequence()
            .filter { it.value.viewAdded != null }
            .forEach {
                current += (it.value.viewAdded as BoxView).getTextBox()
            }
        if (current == mResult) {
            viewModel.addLevel(viewModel.getLevel() + 1)
            preference?.getValueCoin()?.let {
                preference?.setValueCoin(it + 5)
            }
            navigateResult()
        } else if (current.length == mResult.length) {
            if (!viewModel.isEnableSoundFX()) return
            Handler(Looper.getMainLooper()).postDelayed({
                viewModel.playLose()
            }, 100L)
            Handler(Looper.getMainLooper()).postDelayed({
                vibratePhone()
            }, 150L)
        }
    }

    private fun navigateResult() {
        CommonConfetti.rainingConfetti(
            binding.clRoot,
            intArrayOf(Color.YELLOW, Color.GREEN, Color.MAGENTA)
        ).oneShot()
        viewModel.playWin()
        viewModel.addLevel(viewModel.getLevel())
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(GameFragmentDirections.actionGameFragmentToResultFragment())
        }, 2000L)
    }
}