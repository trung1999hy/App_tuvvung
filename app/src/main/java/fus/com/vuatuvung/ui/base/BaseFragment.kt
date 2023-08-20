package fus.com.vuatuvung.ui.base

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import fus.com.vuatuvung.ui.chargecoin.ChargeCoinActivity
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    fun navigateChargeCoin() {
        startActivity(
            Intent(
                requireContext(),
                ChargeCoinActivity::class.java
            )
        )
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