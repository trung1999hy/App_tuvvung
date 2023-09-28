package com.tmt.vuaghepchu.ui.splashScreen

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable

class SplashLoadingView : LottieAnimationView {

    private var isAnimRunning = false
    private var startDelayed = 0L

    companion object {
        const val ASSETS_FOLDER = "home"
    }

    constructor(context: Context?) : super(context) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
    }

    private fun initView() {
        imageAssetsFolder = ASSETS_FOLDER
        setAnimation("$ASSETS_FOLDER/splash_load_anim.json")
        repeatCount = LottieDrawable.INFINITE
    }

    override fun playAnimation() {
        Handler(Looper.getMainLooper()).postDelayed({
            super.playAnimation()
            isAnimRunning = true
        }, startDelayed)
    }

    override fun clearAnimation() {
        super.clearAnimation()
        cancelAnimation()
        isAnimRunning = false
    }

    fun playAnim(isRunning: Boolean) {
        super.playAnimation()
        isAnimRunning = isRunning
    }

    fun setIsAnimRunning(isRunning: Boolean) {
        isAnimRunning = isRunning
    }

    fun setStartDelayed(startDelayed: Long) {
        this.startDelayed = startDelayed
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        when (visibility) {
            View.VISIBLE -> when {
                isAnimRunning -> playAnimation()
            }

            View.INVISIBLE, View.GONE -> when {
                isAnimRunning -> cancelAnimation()
            }
        }
    }
}