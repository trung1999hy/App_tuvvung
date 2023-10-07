package com.tmt.vuaghepchu.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.tmt.vuaghepchu.R
import com.tmt.vuaghepchu.databinding.ActivityMainBinding
import com.tmt.vuaghepchu.ui.base.viewBinding
import com.tmt.vuaghepchu.utils.Preference
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private lateinit var navController: NavController
    private var preference: Preference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setFistInstallApp()
        initView()
    }

    private fun initView() {
        binding.bottomNavigationView.menu.getItem(1).setOnMenuItemClickListener {
            navController.navigate(R.id.homeFragment)
            true
        }
        binding.bottomNavigationView.background = null

        navController = findNavController(R.id.nav_host_fragment)

        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setFistInstallApp() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        preference = Preference.buildInstance(this)
        if (preference?.firstInstall == true) {
            preference?.firstInstall = false
            preference?.setValueCoin(10)
        }
        checkNewDay()
    }

    private fun checkNewDay() {
        val lastTimeStarted = preference?.getValueDate()
        val calendar: Calendar = Calendar.getInstance()
        val today: Int = calendar.get(Calendar.DAY_OF_YEAR)
        if (today != lastTimeStarted) {
            preference?.setValueMiniGame(10)
            preference?.setValueDate(today)
        }
    }
}