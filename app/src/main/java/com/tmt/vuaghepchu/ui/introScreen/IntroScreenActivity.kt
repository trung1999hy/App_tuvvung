package com.tmt.vuaghepchu.ui.introScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import com.tmt.vuaghepchu.R
import com.tmt.vuaghepchu.databinding.ActivitySplashScreenBinding
import com.tmt.vuaghepchu.ui.MainActivity

@AndroidEntryPoint
class IntroScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)
        binding.splashText.animate().alpha(1f).setDuration(1200).start()
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000L)
    }
}