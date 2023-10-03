package com.tmt.vuaghepchu.ui.base

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.fragment.app.Fragment
import com.tmt.vuaghepchu.ui.chargecoin.ChargeCoinActivity
import com.tmt.vuaghepchu.ui.minigame.activitygame.MiniGameActivity
import com.tmt.vuaghepchu.ui.minigame.network.Constant

abstract class BaseFragment : Fragment() {

    fun navigateChargeCoin() {
        startActivity(
            Intent(
                requireContext(),
                ChargeCoinActivity::class.java
            )
        )
    }
    fun navigateMiniGame(link : String) {
        val intent = Intent(requireActivity(), MiniGameActivity::class.java)
        intent.putExtra(Constant.linkGame, link)
        startActivity(intent)
    }

    fun vibratePhone() {
        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(150)
        }
    }
}