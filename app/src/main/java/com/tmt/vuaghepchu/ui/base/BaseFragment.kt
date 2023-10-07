package com.tmt.vuaghepchu.ui.base

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.tmt.vuaghepchu.R
import com.tmt.vuaghepchu.ui.chargecoin.ChargeCoinActivity

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

    fun showSnackBar(
        view: View,
        value: String,
        marginBottom: Int = resources.getDimension(R.dimen.dp_60).toInt(),
        sideMargin: Int = 0
    ) {
        val sb = Snackbar.make(view, value, Snackbar.LENGTH_LONG)
        val snackBarView = sb.view
        val params = snackBarView.layoutParams as CoordinatorLayout.LayoutParams
        params.setMargins(
            params.leftMargin + sideMargin,
            params.topMargin,
            params.rightMargin + sideMargin,
            params.bottomMargin + marginBottom
        )
        snackBarView.layoutParams = params
        sb.show()
    }
}